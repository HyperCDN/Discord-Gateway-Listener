package de.hypercdn.dgl.imp.event.type.internal;

import de.hypercdn.dgl.imp.utils.tuples.Pair;

public class ExceptionEvent extends InternalEvent<Pair<Throwable, Object>> {

    public ExceptionEvent(Throwable throwable, Object reference) {
        super(new Pair<>(throwable, reference));
    }

}
