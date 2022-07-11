package de.hypercdn.dgl.api.auth;

import de.hypercdn.dgl.api.event.Event;

public interface Authorization {

    Event<? extends IdentifyPayload> identifyEvent();

}
