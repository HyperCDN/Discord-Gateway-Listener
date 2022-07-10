package de.hypercdn.dgl.imp.event.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.payload.GuildRequestMembers;
import de.hypercdn.dgl.imp.status.OpCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuildRequestMemberEvent extends GenericEvent<GuildRequestMembers> {

    {
        this.opCode = OpCode.REQUEST_GUILD_MEMBERS;
    }

    public GuildRequestMemberEvent() {
        super(null);
    }

    public GuildRequestMemberEvent(GuildRequestMembers payload) {
        super(payload);
    }
}
