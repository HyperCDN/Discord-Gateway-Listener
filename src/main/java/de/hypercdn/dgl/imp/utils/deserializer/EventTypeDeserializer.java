package de.hypercdn.dgl.imp.utils.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.hypercdn.dgl.imp.event.EventType;

import java.io.IOException;

public class EventTypeDeserializer extends JsonDeserializer<EventType> {
    @Override
    public EventType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return EventType.of(p.getValueAsString());
    }
}
