package com.fsm.exceptions;

import com.fsm.entities.Context;
import com.fsm.models.Event;
import com.fsm.models.State;
import lombok.Getter;

@Getter
public class FSMRunTimeException extends RuntimeException {

    private final State state;
    private final Event event;
    private final Context context;

    public FSMRunTimeException(
            State state, Event event, Exception error, String message, Context context) {
        super(message, error);

        this.state = state;
        this.event = event;
        this.context = context;
    }
}
