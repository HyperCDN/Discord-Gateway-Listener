package de.hypercdn.dgl.imp.event.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Identify(
        @JsonProperty("token") String token,
        @JsonProperty("properties") Properties properties,
        @JsonProperty("compress") Boolean compress,
        @JsonProperty("large_threshold") Integer largeThreshold,
        @JsonProperty("shard") Integer[] shard,
        @JsonProperty("presence") UpdatePresence presence,
        @JsonProperty("intents") Integer intents) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Properties(
            @JsonProperty("$os") String os,
            @JsonProperty("$browser") String browser,
            @JsonProperty("$device") String device) {
    }
}
