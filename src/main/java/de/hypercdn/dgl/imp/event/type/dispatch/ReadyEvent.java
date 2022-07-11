package de.hypercdn.dgl.imp.event.type.dispatch;

import de.hypercdn.dgl.imp.event.payload.Ready;
import de.hypercdn.dgl.imp.event.type.DispatchEvent;

public class ReadyEvent extends DispatchEvent<Ready> {

    public ReadyEvent() {
        super(null);
    }

    public ReadyEvent(Ready ready) {
        super(ready);
    }

}
