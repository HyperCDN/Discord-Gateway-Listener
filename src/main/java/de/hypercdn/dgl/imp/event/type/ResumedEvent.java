package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.Resume;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumedEvent extends GenericEvent<Resume> {

    {
        this.opCode = OpCode.RESUME;
    }

    public ResumedEvent() {
        super(null);
    }

    public ResumedEvent(Resume payload) {
        super(payload);
    }
}
