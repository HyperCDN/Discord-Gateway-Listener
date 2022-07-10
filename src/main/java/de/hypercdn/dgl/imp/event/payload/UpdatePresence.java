package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdatePresence(
        @JsonProperty("since") Long since,
        @JsonProperty("activities") ArrayNode activities,
        @JsonProperty("status") String status,
        @JsonProperty("afk") Boolean afk) {
}
