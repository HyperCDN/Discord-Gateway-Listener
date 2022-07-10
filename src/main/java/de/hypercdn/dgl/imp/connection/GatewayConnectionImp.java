package de.hypercdn.dgl.imp.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventManager;
import de.hypercdn.dgl.imp.connection.utils.Connect;
import de.hypercdn.dgl.imp.connection.utils.Heart;
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
    private final Heart heart;
    private final Connect connect;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private WebSocket webSocket;

    public GatewayConnectionImp(OkHttpClient okHttpClient, Authorization authorization, GatewayConnection.Details details, EventManager eventManager) {
        this.okHttpClient = okHttpClient;
        this.details = details;
        this.eventManager = eventManager;
        this.heart = new Heart();
        this.connect = new Connect(authorization);
    }

    @Override
    public EventManager eventManager() {
        return eventManager;
    }

    @Override
    public synchronized void open() {
        if (webSocket != null) {
            throw new IllegalStateException("Websocket already open");
        }
        var request = new Request.Builder()
                .url(GATEWAY_URL + details.asParamString())
                .build();
        webSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {

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
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                System.out.println(response);
                t.printStackTrace();
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }
        });
    }

    @Override
    public synchronized void close() {
        // close and reset
        if (webSocket == null) {
            throw new IllegalStateException("Websocket already closed");
        }
        heart.stopAndReset();
        webSocket.close(1000, "Closing"); // ??
        webSocket = null;
    }

    @Override
    public void send(Event<?> event) {
        try {
            var serialized = objectMapper.writeValueAsString(event);
            webSocket.send(serialized);
            logger.debug("<<< " + event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
