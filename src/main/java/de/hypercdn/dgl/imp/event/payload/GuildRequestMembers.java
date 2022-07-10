package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GuildRequestMembers(
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("query") String query,
        @JsonProperty("limit") Integer Limit,
        @JsonProperty("presence") Boolean presence,
        @JsonProperty("user_ids") String[] userIds,
        @JsonProperty("nonce") String nonce) {
}
