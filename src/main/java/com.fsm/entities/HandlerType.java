package com.fsm.entities;

import com.fsm.models.Event;
import com.fsm.models.State;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandlerType {

    private EventType eventType;
    private Event event;
    private State state;

    public HandlerType(EventType eventType) {
        this.eventType = eventType;
    }
}
