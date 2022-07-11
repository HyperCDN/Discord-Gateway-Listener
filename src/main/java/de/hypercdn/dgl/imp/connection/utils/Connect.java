package de.hypercdn.dgl.imp.connection.utils;

import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.auth.IdentifyPayload;
import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventListener;
import de.hypercdn.dgl.imp.event.payload.Ready;
import de.hypercdn.dgl.imp.event.payload.Resume;
import de.hypercdn.dgl.imp.event.type.GatewayHelloEvent;
import de.hypercdn.dgl.imp.event.type.ReconnectEvent;
import de.hypercdn.dgl.imp.event.type.ResumedEvent;
import de.hypercdn.dgl.imp.event.type.dispatch.ReadyEvent;
import de.hypercdn.dgl.imp.event.type.internal.ConnectionEvent;

public class Connect implements EventListener {

    private final Authorization authorization;
    private Ready ready;

    public Connect(Authorization authorization) {
        this.authorization = authorization;
    }

    public void reset() {
        ready = null;
    }

    @Override
    public void onEvent(GatewayConnection gatewayConnection, Event<?> event) {
        if (event instanceof GatewayHelloEvent) {
            if (ready == null) {
                gatewayConnection.notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.IDENTIFYING));

                gatewayConnection.send(authorization.identifyEvent());
            } else {
                gatewayConnection.notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.RESUMING));

                IdentifyPayload identify = authorization.identifyEvent().payload();
                gatewayConnection.send(new ResumedEvent(new Resume(
                        identify.token(),
                        ready.sessionId(),
                        null
                )));
            }
        } else if (event instanceof ReconnectEvent reconnectEvent) {
            gatewayConnection.notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.RESUMED));

        } else if (event instanceof ReadyEvent readyEvent) {
            gatewayConnection.notifyInternal(new ConnectionEvent(ConnectionEvent.ConnectionStatus.READY));
            this.ready = readyEvent.payload();

        }
    }

}
