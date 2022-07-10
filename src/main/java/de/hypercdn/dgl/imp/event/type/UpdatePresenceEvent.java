package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.UpdatePresence;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePresenceEvent extends GenericEvent<UpdatePresence> {

    {
        this.opCode = OpCode.PRESENCE_UPDATE;
    }

    public UpdatePresenceEvent() {
        super(null);
    }

    public UpdatePresenceEvent(UpdatePresence payload) {
        super(payload);
    }
}
