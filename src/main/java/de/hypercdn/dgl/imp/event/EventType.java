package de.hypercdn.dgl.imp.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.hypercdn.dgl.imp.event.payload.GatewayHello;
import de.hypercdn.dgl.imp.event.payload.Ready;
import de.hypercdn.dgl.imp.event.payload.Resume;
import de.hypercdn.dgl.imp.utils.deserializer.EventTypeDeserializer;
import de.hypercdn.dgl.imp.utils.serializer.EventTypeSerializer;

import java.util.Arrays;

@JsonSerialize(using = EventTypeSerializer.class)
@JsonDeserialize(using = EventTypeDeserializer.class)
public enum EventType {
    HELLO(GatewayHello.class),
    READY(Ready.class),
    RESUMES(Resume.class),
    RECONNECT(Object.class),
    INVALID_SESSION(Object.class),
    APPLICATION_COMMAND_PERMISSION_UPDATE(Object.class),
    AUTO_MODERATION_RULE_CREATE(Object.class),
    AUTO_MODERATION_RULE_UPDATE(Object.class),
    AUTO_MODERATION_RULE_DELETE(Object.class),
    AUTO_MODERATION_ACTION_EXECUTION(Object.class),
    CHANNEL_CREATE(Object.class),
    CHANNEL_UPDATE(Object.class),
    CHANNEL_DELETE(Object.class),
    CHANNEL_PINS_UPDATE(Object.class),
    THREAD_CREATE(Object.class),
    THREAD_UPDATE(Object.class),
    THREAD_DELETE(Object.class),
    THREAD_LIST_SYNC(Object.class),
    THREAD_MEMBER_UPDATE(Object.class),
    THREAD_MEMBERS_UPDATE(Object.class),
    GUILD_CREATE(Object.class),
    GUILD_UPDATE(Object.class),
    GUILD_DELETE(Object.class),
    GUILD_BAN_ADD(Object.class),
    GUILD_BAN_REMOVE(Object.class),
    GUILD_EMOJI_UPDATE(Object.class),
    GUILD_INTEGRATIONS_UPDATE(Object.class),
    GUILD_MEMBER_ADD(Object.class),
    GUILD_MEMBER_REMOVE(Object.class),
    GUILD_MEMBER_UPDATE(Object.class),
    GUILD_MEMBERS_CHUNK(Object.class),
    GUILD_ROLE_CREATE(Object.class),
    GUILD_ROLE_UPDATE(Object.class),
    GUILD_ROLE_DELETE(Object.class),
    GUILD_SYNC(Object.class),
    GUILD_VOICE_STATE_UPDATE(Object.class),
    GUILD_EMBED_TEMPLATE_UPDATE(Object.class),
    GUILD_INTEGRATION_CREATE(Object.class),
    GUILD_INTEGRATION_UPDATE(Object.class),
    GUILD_INTEGRATION_DELETE(Object.class),
    INVITE_CREATE(Object.class),
    INVITE_DELETE(Object.class),
    MESSAGE_CREATE(Object.class),
    MESSAGE_UPDATE(Object.class),
    MESSAGE_DELETE(Object.class),
    MESSAGE_DELETE_BULK(Object.class),
    MESSAGE_REACTION_ADD(Object.class),
    MESSAGE_REACTION_REMOVE(Object.class),
    MESSAGE_REACTION_REMOVE_ALL(Object.class),
    MESSAGE_REACTION_REMOVE_EMOJI(Object.class),
    PRESENCE_UPDATE(Object.class),
    STAGE_INSTANCE_CREATE(Object.class),
    STAGE_INSTANCE_UPDATE(Object.class),
    STAGE_INSTANCE_DELETE(Object.class),
    TYPING_START(Object.class),
    USER_UPDATE(Object.class),
    Voice_STATE_UPDATE(Object.class),
    VOICE_SERVER_UPDATE(Object.class),
    WEBHOOKS_UPDATE(Object.class),
    ;

    private final Class<?> payload;

    EventType(Class<?> payload) {
        this.payload = payload;
    }

    public static EventType of(String type) {
        return Arrays.stream(EventType.values()).filter(e -> e.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public Class<?> payloadClass() {
        return payload;
    }

}