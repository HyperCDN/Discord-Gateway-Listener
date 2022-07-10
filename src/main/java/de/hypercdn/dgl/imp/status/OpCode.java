package de.hypercdn.dgl.imp.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.hypercdn.dgl.api.status.StatusCode;
import de.hypercdn.dgl.imp.event.GenericEvent;
import de.hypercdn.dgl.imp.event.type.*;
import de.hypercdn.dgl.imp.utils.deserializer.OpCodeDeserializer;
import de.hypercdn.dgl.imp.utils.serializer.OpCodeSerializer;

import java.util.Arrays;

@JsonSerialize(using = OpCodeSerializer.class)
@JsonDeserialize(using = OpCodeDeserializer.class)
public enum OpCode implements StatusCode {
    UNKNOWN(-1, "UNKNOWN OP CODE", GenericEvent.class),
    DISPATCH(0, "An event was dispatched.", DispatchEvent.class),
    HEARTBEAT(1, "Fired periodically by the client to keep the connection alive.", HeartbeatEvent.class),
    IDENTIFY(2, "Starts a new session during the initial handshake.", IdentifyEvent.class),
    PRESENCE_UPDATE(3, "Update the client's presence.", UpdatePresenceEvent.class),
    VOICE_STATE_UPDATE(4, "Used to join/leave or move between voice channels.", UpdateVoiceStateEvent.class),
    RESUME(6, "Resume a previous session that was disconnected.", ResumedEvent.class),
    RECONNECT(7, "You should attempt to reconnect and resume immediately.", ReconnectEvent.class),
    REQUEST_GUILD_MEMBERS(8, "Request information about offline guild members in a large guild.", GuildRequestMemberEvent.class),
    INVALID_SESSION(9, "The session has been invalidated. You should reconnect and identify/resume accordingly.", InvalidSessionEvent.class),
    HELLO(10, "Sent immediately after connecting, contains the heartbeat_interval to use.", GatewayHelloEvent.class),
    HEARTBEAT_ACK(11, "Sent in response to receiving a heartbeat to acknowledge that it has been received.", HeartbeatAckEvent.class),
    ;

    private final int intValue;
    private final String description;

    private final Class<?> clazz;

    OpCode(int intValue, String description, Class<?> clazz) {
        this.intValue = intValue;
        this.description = description;
        this.clazz = clazz;
    }

    public static OpCode of(int value) {
        return Arrays.stream(OpCode.values()).filter(e -> e.intValue == value).findFirst().orElse(UNKNOWN);
    }

    @Override
    public Integer intValue() {
        return intValue;
    }

    @Override
    public String description() {
        return description;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
