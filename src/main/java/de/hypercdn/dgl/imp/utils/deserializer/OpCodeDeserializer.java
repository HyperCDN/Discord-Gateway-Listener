package de.hypercdn.dgl.imp.utils.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.hypercdn.dgl.imp.status.OpCode;

import java.io.IOException;

public class OpCodeDeserializer extends JsonDeserializer<OpCode> {
    @Override
    public OpCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return OpCode.of(p.getIntValue());
    }
}
