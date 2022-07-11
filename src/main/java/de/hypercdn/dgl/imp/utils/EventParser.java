package de.hypercdn.dgl.imp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.imp.event.EventType;
import de.hypercdn.dgl.imp.event.type.DispatchEvent;
import de.hypercdn.dgl.imp.status.OpCode;

public class EventParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Event<?> parse(String jsonString) throws JsonProcessingException {
        var json = (JsonNode) objectMapper.readTree(jsonString);
        var opNode = json.get("op");
        var opCode = OpCode.of(opNode != null ? opNode.intValue() : -1);
        if (opCode != OpCode.DISPATCH) {
            return (Event<?>) objectMapper.treeToValue(json, opCode.getClazz());
        }
        var tNode = json.get("t");
        var type = EventType.of(tNode != null ? tNode.asText() : null);
        if (type == null || type.getClazz() == null) {
            return objectMapper.treeToValue(json, DispatchEvent.class);
        }
        return (Event<?>) objectMapper.treeToValue(json, type.getClazz());
    }

}
