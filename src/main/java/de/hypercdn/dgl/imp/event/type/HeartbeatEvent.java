package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeartbeatEvent extends GenericEvent<Integer> {

    {
        this.opCode = OpCode.HEARTBEAT;
    }

    public HeartbeatEvent() {
        super(null);
    }

    public HeartbeatEvent(Integer payload) {
        super(payload);
    }
}
