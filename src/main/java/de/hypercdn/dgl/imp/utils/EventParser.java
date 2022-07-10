package de.hypercdn.dgl.imp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.imp.status.OpCode;

public class EventParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Event<?> parse(String jsonString) throws JsonProcessingException {
        var json = (JsonNode) objectMapper.readTree(jsonString);
        var opNode = json.get("op");
        var opCode = OpCode.of(opNode != null ? opNode.intValue() : -1);
        return (Event<?>) objectMapper.treeToValue(json, opCode.getClazz());
    }

}
