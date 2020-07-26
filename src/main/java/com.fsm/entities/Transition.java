package com.fsm.entities;

import com.fsm.models.Event;
import com.fsm.models.State;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transition {

    private Event event;
    private State from;
    private State to;
}
