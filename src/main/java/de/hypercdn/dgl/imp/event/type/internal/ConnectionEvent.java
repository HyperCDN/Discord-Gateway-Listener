package de.hypercdn.dgl.imp.event.type.internal;

public class ConnectionEvent extends InternalEvent<ConnectionEvent.ConnectionStatus> {

    public ConnectionEvent(ConnectionStatus connectionStatus) {
        super(connectionStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + payload + ")";
    }

    public enum ConnectionStatus {
        CONNECTING,
        CONNECTION_OPENED,
        IDENTIFYING,
        RESUMING,
        RESUMED,
        READY,

        CLOSED
    }

}
