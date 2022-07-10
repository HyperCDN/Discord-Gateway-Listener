package de.hypercdn.dgl.imp.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.hypercdn.dgl.imp.event.EventType;

import java.io.IOException;

public class EventTypeSerializer extends JsonSerializer<EventType> {
    @Override
    public void serialize(EventType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.name());
    }
}
