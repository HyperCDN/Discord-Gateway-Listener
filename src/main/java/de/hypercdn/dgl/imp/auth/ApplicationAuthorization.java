package de.hypercdn.dgl.imp.auth;

import de.hypercdn.dgl.api.auth.Authorization;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.imp.event.payload.Identify;
import de.hypercdn.dgl.imp.event.payload.UpdatePresence;
import de.hypercdn.dgl.imp.event.type.IdentifyEvent;

public class ApplicationAuthorization implements Authorization {

    private final IdentifyEvent identifyEvent;

    public ApplicationAuthorization(String token, Integer[] shard, UpdatePresence presence, Integer intents) {
        var payload = new Identify(
                token,
                new Identify.Properties(
                        System.getProperty("os.name"),
                        "NetBeacon-DGL",
                        "NetBeacon-DGL"
                ),
                false,
                250,
                shard,
                presence,
                intents
        );
        this.identifyEvent = new IdentifyEvent(payload);
    }

    @Override
    public Event<?> identifyEvent() {
        return identifyEvent;
    }
}
