package de.hypercdn.dgl.api.connection;

import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventManager;
import de.hypercdn.dgl.imp.connection.GatewayConnectionImp;
import de.hypercdn.dgl.imp.event.GenericEventManager;
import de.hypercdn.dgl.imp.event.type.internal.InternalEvent;
import okhttp3.OkHttpClient;

public interface GatewayConnection extends AutoCloseable {

    String BASE_URL = "https://discord.com/api";
    String GATEWAY_URL_FALLBACK = "wss://gateway.discord.gg/";

    static GatewayConnection createDefault(Details details, Authorization authorization) {
        return createDefault(new OkHttpClient(), details, authorization, true);
    }

    static GatewayConnection createDefault(OkHttpClient okHttpClient, Details details, Authorization authorization, boolean reconnect) {
        return new GatewayConnectionImp(okHttpClient, authorization, details, new GenericEventManager(), reconnect);
    }

    EventManager eventManager();

    boolean doesReconnect();

    void enableReconnect(boolean state);

    void open();

    void close();

    void send(Event<?> event);

    void notifyInternal(InternalEvent<?> internalEvent);

    record Details(
            Integer apiVersion,
            String encoding,
            String compression) {
        public String asGatewayParamString() {
            return "?v=" + apiVersion + "&encoding=" + encoding + ((compression != null) ? "&compress=" + compression : "");
        }
    }
}
