package de.hypercdn.dgl.imp.event;

import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventListener;
import de.hypercdn.dgl.api.event.EventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericEventManager implements EventManager {

    private final List<EventListener> listeners = new ArrayList<>();
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(GatewayConnection gatewayConnection, Event<?> event) {
        System.out.println(event.toString());
        for (var listener : new ArrayList<>(listeners)) {
            listener.onEvent(gatewayConnection, event);
        }
    }


    @Override
    public void addEventListeners(EventListener... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    public void removeEventListeners(EventListener... listeners) {
        this.listeners.removeAll(Arrays.asList(listeners));
    }
}
