package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Resume(
        @JsonProperty("token") String token,
        @JsonProperty("session_id") String sessionId,
        @JsonProperty("seq") Long sequence) {
}
