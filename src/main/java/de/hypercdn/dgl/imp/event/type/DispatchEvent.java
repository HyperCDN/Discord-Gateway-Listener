package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchEvent<T> extends GenericEvent<T> {

    {
        this.opCode = OpCode.DISPATCH;
    }

    public DispatchEvent() {
        super(null);
    }

    public DispatchEvent(T payload) {
        super(payload);
    }
}
