package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.Identify;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentifyEvent extends GenericEvent<Identify> {

    {
        this.opCode = OpCode.IDENTIFY;
    }

    public IdentifyEvent() {
        super(null);
    }

    public IdentifyEvent(Identify payload) {
        super(payload);
    }
}
