package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvalidSessionEvent extends GenericEvent<Boolean> {

    {
        this.opCode = OpCode.INVALID_SESSION;
    }

    public InvalidSessionEvent() {
        super(null);
    }

    public InvalidSessionEvent(Boolean payload) {
        super(payload);
    }
}
