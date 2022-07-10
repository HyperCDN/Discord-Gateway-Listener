package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Ready(
        @JsonProperty("v") Integer apiVersion,
        @JsonProperty("user") ObjectNode user,
        @JsonProperty("guilds") ArrayNode guilds,
        @JsonProperty("session_id") String sessionId,
        @JsonProperty("shard") Integer[] shard,
        @JsonProperty("application") ObjectNode application) {
}
