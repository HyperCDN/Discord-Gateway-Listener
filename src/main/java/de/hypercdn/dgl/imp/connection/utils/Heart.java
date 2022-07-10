package de.hypercdn.dgl.imp.connection.utils;

import de.hypercdn.dgl.api.connection.GatewayConnection;
import de.hypercdn.dgl.api.event.Event;
import de.hypercdn.dgl.api.event.EventListener;
import de.hypercdn.dgl.imp.event.type.GatewayHelloEvent;
import de.hypercdn.dgl.imp.event.type.HeartbeatAckEvent;
import de.hypercdn.dgl.imp.event.type.HeartbeatEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Heart implements EventListener {

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final AtomicInteger counter = new AtomicInteger(0);
    private Future<?> heartbeatTask;

    public void start(GatewayConnection gatewayConnection, long delay) {
        heartbeatTask = executor.scheduleAtFixedRate(() -> {
            gatewayConnection.send(new HeartbeatEvent(counter.get()));
        }, delay, delay, TimeUnit.MILLISECONDS);
    }

    public void stopAndReset() {
        if (heartbeatTask != null) {
            heartbeatTask.cancel(true);
        }
        heartbeatTask = null;
        counter.set(0);
    }

    @Override
    public void onEvent(GatewayConnection gatewayConnection, Event<?> event) {
        if (event instanceof GatewayHelloEvent gatewayHelloEvent) {
            this.stopAndReset();
            this.start(gatewayConnection, gatewayHelloEvent.payload().heartbeatInterval());
        } else if (event instanceof HeartbeatAckEvent heartbeatAckEvent) {
            counter.incrementAndGet();
        }
    }
}
