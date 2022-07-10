package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.UpdateVoiceState;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateVoiceStateEvent extends GenericEvent<UpdateVoiceState> {

    {
        this.opCode = OpCode.VOICE_STATE_UPDATE;
    }

    public UpdateVoiceStateEvent() {
        super(null);
    }

    public UpdateVoiceStateEvent(UpdateVoiceState payload) {
        super(payload);
    }
}
