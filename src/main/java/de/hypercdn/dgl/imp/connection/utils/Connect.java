package de.hypercdn.dgl.imp.connection.utils;

import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventListener;
import de.hypercdn.dgl.imp.event.type.GatewayHelloEvent;

public class Connect implements EventListener {

    private final Authorization authorization;

    public Connect(Authorization authorization) {
        this.authorization = authorization;
    }

    @Override
    public void onEvent(GatewayConnection gatewayConnection, Event<?> event) {
        if (event instanceof GatewayHelloEvent) {
            gatewayConnection.send(authorization.identifyEvent());
        }
    }

}
