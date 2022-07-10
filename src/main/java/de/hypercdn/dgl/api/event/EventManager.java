package de.hypercdn.dgl.api.event;

public interface EventManager extends EventListener {

    void addEventListeners(EventListener... listeners);

    void removeEventListeners(EventListener... listeners);

}
