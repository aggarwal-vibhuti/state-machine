package com.fsm.entities;

import com.fsm.models.Event;
import com.fsm.models.State;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Context {

    private State from;
    private State to;
    private Event event;
}
