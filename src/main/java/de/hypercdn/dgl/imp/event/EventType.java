package de.hypercdn.dgl.imp.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.hypercdn.dgl.imp.event.type.GatewayHelloEvent;
import de.hypercdn.dgl.imp.event.type.dispatch.ReadyEvent;
import de.hypercdn.dgl.imp.utils.deserializer.EventTypeDeserializer;
import de.hypercdn.dgl.imp.utils.serializer.EventTypeSerializer;

import java.util.Arrays;

@JsonSerialize(using = EventTypeSerializer.class)
@JsonDeserialize(using = EventTypeDeserializer.class)
public enum EventType {
    HELLO(GatewayHelloEvent.class),
    READY(ReadyEvent.class),
    RESUMES(null),
    RECONNECT(null),
    INVALID_SESSION(null),
    APPLICATION_COMMAND_PERMISSION_UPDATE(null),
    AUTO_MODERATION_RULE_CREATE(null),
    AUTO_MODERATION_RULE_UPDATE(null),
    AUTO_MODERATION_RULE_DELETE(null),
    AUTO_MODERATION_ACTION_EXECUTION(null),
    CHANNEL_CREATE(null),
    CHANNEL_UPDATE(null),
    CHANNEL_DELETE(null),
    CHANNEL_PINS_UPDATE(null),
    THREAD_CREATE(null),
    THREAD_UPDATE(null),
    THREAD_DELETE(null),
    THREAD_LIST_SYNC(null),
    THREAD_MEMBER_UPDATE(null),
    THREAD_MEMBERS_UPDATE(null),
    GUILD_CREATE(null),
    GUILD_UPDATE(null),
    GUILD_DELETE(null),
    GUILD_BAN_ADD(null),
    GUILD_BAN_REMOVE(null),
    GUILD_EMOJI_UPDATE(null),
    GUILD_INTEGRATIONS_UPDATE(null),
    GUILD_MEMBER_ADD(null),
    GUILD_MEMBER_REMOVE(null),
    GUILD_MEMBER_UPDATE(null),
    GUILD_MEMBERS_CHUNK(null),
    GUILD_ROLE_CREATE(null),
    GUILD_ROLE_UPDATE(null),
    GUILD_ROLE_DELETE(null),
    GUILD_SYNC(null),
    GUILD_VOICE_STATE_UPDATE(null),
    GUILD_EMBED_TEMPLATE_UPDATE(null),
    GUILD_INTEGRATION_CREATE(null),
    GUILD_INTEGRATION_UPDATE(null),
    GUILD_INTEGRATION_DELETE(null),
    INVITE_CREATE(null),
    INVITE_DELETE(null),
    MESSAGE_CREATE(null),
    MESSAGE_UPDATE(null),
    MESSAGE_DELETE(null),
    MESSAGE_DELETE_BULK(null),
    MESSAGE_REACTION_ADD(null),
    MESSAGE_REACTION_REMOVE(null),
    MESSAGE_REACTION_REMOVE_ALL(null),
    MESSAGE_REACTION_REMOVE_EMOJI(null),
    PRESENCE_UPDATE(null),
    STAGE_INSTANCE_CREATE(null),
    STAGE_INSTANCE_UPDATE(null),
    STAGE_INSTANCE_DELETE(null),
    TYPING_START(null),
    USER_UPDATE(null),
    Voice_STATE_UPDATE(null),
    VOICE_SERVER_UPDATE(null),
    WEBHOOKS_UPDATE(null),
    ;

    private final Class<?> clazz;

    EventType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static EventType of(String type) {
        return Arrays.stream(EventType.values()).filter(e -> e.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public Class<?> getClazz() {
        return clazz;
    }

}