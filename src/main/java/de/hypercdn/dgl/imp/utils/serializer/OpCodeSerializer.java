package de.hypercdn.dgl.imp.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.hypercdn.dgl.imp.status.OpCode;

import java.io.IOException;

public class OpCodeSerializer extends JsonSerializer<OpCode> {
    @Override
    public void serialize(OpCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.intValue());
    }
}
