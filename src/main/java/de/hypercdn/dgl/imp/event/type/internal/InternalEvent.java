package de.hypercdn.dgl.imp.event.type.internal;

import de.hypercdn.dgl.imp.event.GenericEvent;

public class InternalEvent<T> extends GenericEvent<T> {

    {
        opCode = null;
    }

    public InternalEvent() {
        super(null);
    }

    public InternalEvent(T payload) {
        super(payload);
    }

}
