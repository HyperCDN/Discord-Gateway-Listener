package de.hypercdn.dgl.api.connection;

import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventManager;
import de.hypercdn.dgl.imp.connection.GatewayConnectionImp;
import de.hypercdn.dgl.imp.event.GenericEventManager;
import okhttp3.OkHttpClient;

public interface GatewayConnection extends AutoCloseable {

    static String GATEWAY_URL = "wss://gateway.discord.gg/";

    static GatewayConnection createDefault(Details details, Authorization authorization) {
        return createDefault(new OkHttpClient(), details, authorization);
    }

    static GatewayConnection createDefault(OkHttpClient okHttpClient, Details details, Authorization authorization) {
        return new GatewayConnectionImp(okHttpClient, authorization, details, new GenericEventManager());
    }

    EventManager eventManager();

    void open();

    void close();

    void send(Event<?> event);

    record Details(
            Integer apiVersion,
            String encoding,
            String compression) {

        public String asParamString() {
            return "?v=" + apiVersion + "&encoding=" + encoding + ((compression != null) ? "&compress=" + compression : "");
        }
    }
}
