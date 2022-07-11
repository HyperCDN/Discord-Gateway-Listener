package de.hypercdn.dgl.imp.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventManager;
import de.hypercdn.dgl.imp.connection.utils.Connect;
import de.hypercdn.dgl.imp.connection.utils.Heart;
import de.hypercdn.dgl.imp.event.type.internal.ConnectionEvent;
import de.hypercdn.dgl.imp.event.type.internal.ExceptionEvent;
import de.hypercdn.dgl.imp.event.type.internal.InternalEvent;
import de.hypercdn.dgl.imp.status.CloseCode;
import de.hypercdn.dgl.imp.utils.EventParser;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayConnectionImp implements GatewayConnection {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient okHttpClient;
    private final GatewayConnection.Details details;
    private final EventManager eventManager;

    private boolean reconnect;
    private final Heart heart;
    private final Connect connect;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private WebSocket webSocket;

    public GatewayConnectionImp(OkHttpClient okHttpClient, Authorization authorization, GatewayConnection.Details details, EventManager eventManager, boolean reconnect) {
        this.okHttpClient = okHttpClient;
        this.details = details;
        this.eventManager = eventManager;
        this.heart = new Heart();
        this.connect = new Connect(authorization);
        this.reconnect = reconnect;
    }

    @Override
    public EventManager eventManager() {
        return eventManager;
    }

    @Override
    public boolean doesReconnect() {
        return reconnect;
    }

    @Override
    public void enableReconnect(boolean state) {
        this.reconnect = state;
    }

    @Override
    public synchronized void open() {
        if (webSocket != null) {
            throw new IllegalStateException("Websocket already open");
        }
        notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.CONNECTING));
        String GATEWAY_URL = GATEWAY_URL_FALLBACK;
        var gatewayResolve = new Request.Builder()
                .url(BASE_URL + "gateway")
                .build();
        try (var gatewayResolveResponse = okHttpClient.newCall(gatewayResolve).execute()) {
            if (gatewayResolveResponse.isSuccessful()) {
                try (var body = gatewayResolveResponse.body()) {
                    var json = objectMapper.readValue(body.string(), ObjectNode.class);
                    GATEWAY_URL = json.get("url").asText();
                }
            }
        } catch (Exception e) { /* swallow */ }

        var request = new Request.Builder()
                .url(GATEWAY_URL + details.asGatewayParamString())
                .build();
        webSocket = okHttpClient.newWebSocket(request, listener);
    }

    @Override
    public synchronized void close() {
        // stop heartbeat and close
        heart.stopAndReset();
        webSocket.close(1000, "Closing"); // ??
        webSocket = null;
        // do not reset connection as we might want to resume
        // connect.reset();
        notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.CLOSED));
    }

    WebSocketListener listener = new WebSocketListener() {

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.CONNECTION_OPENED));
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            try {
                var event = EventParser.parse(text);
                logger.debug(">>>" + event);
                // manual feed
                heart.onEvent(GatewayConnectionImp.this, event);
                connect.onEvent(GatewayConnectionImp.this, event);
                // event manager
                    eventManager.onEvent(GatewayConnectionImp.this, event);
                } catch (JsonProcessingException e) {
                notifyInternal(new ExceptionEvent(e, text));
                }
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                notifyInternal(new InternalEvent<>(t));
            }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            webSocket.close(code, reason); // should close ws as advised
        }

        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            var closeCode = CloseCode.of(code);
            if (closeCode.reconnect() && reconnect) {
                // allow for reconnect
                GatewayConnectionImp.this.close();
                GatewayConnectionImp.this.open();
            } else {
                // mark as closed, stop everything
                GatewayConnectionImp.this.close();
                connect.reset(); // clear session codes etc
            }
        }
    };

    @Override
    public void send(Event<?> event) {
        try {
            var serialized = objectMapper.writeValueAsString(event);
            logger.debug("<<< " + event);
            webSocket.send(serialized);
        } catch (Exception e) {
            notifyInternal(new ExceptionEvent(e, event));
        }
    }

    @Override
    public void notifyInternal(InternalEvent<?> internalEvent) {
        logger.debug("<<<>>>" + internalEvent);
        eventManager.onEvent(this, internalEvent);
    }


}
