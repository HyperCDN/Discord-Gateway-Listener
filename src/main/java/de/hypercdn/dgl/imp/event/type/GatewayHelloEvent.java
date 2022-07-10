package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.GatewayHello;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GatewayHelloEvent extends GenericEvent<GatewayHello> {

    {
        this.opCode = OpCode.HELLO;
    }

    public GatewayHelloEvent() {
        super(null);
    }

    public GatewayHelloEvent(GatewayHello payload) {
        super(payload);
    }

}
