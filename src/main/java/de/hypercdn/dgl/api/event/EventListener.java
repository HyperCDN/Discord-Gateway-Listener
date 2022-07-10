package de.hypercdn.dgl.api.event;

import de.hypercdn.dgl.api.connection.GatewayConnection;

public interface EventListener {

    void onEvent(GatewayConnection gatewayConnection, Event<?> event);

}
