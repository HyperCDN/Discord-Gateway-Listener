package de.hypercdn.dgl.imp.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.imp.status.OpCode;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericEvent<T> implements Event<T> {

    @JsonProperty("op")
    protected OpCode opCode = null;
    @JsonProperty("d")
    protected T payload = null;
    @JsonProperty("s")
    protected Integer sequence = null;
    @JsonProperty("t")
    protected EventType type = null;

    public GenericEvent(T payload) {
        this.payload = payload;
    }

    @JsonProperty("op")
    @Override
    public OpCode opCode() {
        return opCode;
    }

    @JsonProperty("d")
    @Nullable
    @Override
    public T payload() {
        return payload;
    }

    @JsonProperty("s")
    @Nullable
    @Override
    public Integer sequence() {
        return sequence;
    }

    @JsonProperty("t")
    @Nullable
    @Override
    public EventType type() {
        return type;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (op: " + opCode + ", t: " + type + ", s: " + sequence + ", payload: " + (payload != null ? "yes" : "no") + ")";
    }
}
