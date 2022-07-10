package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateVoiceState(
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("channel_id") String channelId,
        @JsonProperty("self_mute") Boolean selfMute,
        @JsonProperty("self_deaf") Boolean selfDeaf) {
}
