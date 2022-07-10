package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchEvent extends GenericEvent<Object> {
    protected static ObjectMapper objectMapper = new ObjectMapper();

    {
        this.opCode = OpCode.DISPATCH;
    }

    public DispatchEvent() {
        super(null);
    }

    public DispatchEvent(Object payload) {
        super(payload);
    }

    <T> T typeParsedPayload() throws JsonProcessingException {
        var type = type();
        if (type == null) {
            return (T) payload();
        }
        return (T) objectMapper.readValue(objectMapper.writeValueAsString(payload()), type.payloadClass());
    }
}
