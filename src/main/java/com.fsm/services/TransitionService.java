package com.fsm.services;

import com.fsm.entities.Transition;
import com.fsm.models.Event;
import com.fsm.models.State;
import com.google.common.collect.Multimap;

import java.util.Optional;

public interface TransitionService {

    void addTransition(State state, Transition transition);

    Optional<Transition> getTransition(State from, Event event);

    Multimap<State, Transition> getTransitionDetails();
}
