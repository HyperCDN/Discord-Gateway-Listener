package de.hypercdn.dgl.api.event;

import de.hypercdn.dgl.imp.event.EventType;
import de.hypercdn.dgl.imp.status.OpCode;


public interface Event<T> {

    OpCode opCode();

    T payload();

    Integer sequence();

    EventType type();

}
