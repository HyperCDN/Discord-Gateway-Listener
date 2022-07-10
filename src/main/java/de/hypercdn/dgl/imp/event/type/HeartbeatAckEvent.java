package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeartbeatAckEvent extends GenericEvent<Void> {

    {
        this.opCode = OpCode.HEARTBEAT_ACK;
    }

    public HeartbeatAckEvent() {
        super(null);
    }

    public HeartbeatAckEvent(Void payload) {
        super(payload);
    }
}
