package com.fsm.services.impl;

import com.fsm.entities.Transition;
import com.fsm.models.Event;
import com.fsm.models.State;
import com.fsm.services.TransitionService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

public class TransitionServiceImpl implements TransitionService {

    private final Multimap<State, Transition> transitionDetails;

    public TransitionServiceImpl() {
        transitionDetails = HashMultimap.create();
    }

    @Override
    public void addTransition(State state, Transition transition) {
        transitionDetails.put(state, transition);
    }

    @Override
    public Optional<Transition> getTransition(State from, Event event) {
        return transitionDetails.get(from).stream().filter(new TransitionPredicate(event)).findFirst();
    }

    @Override
    public Multimap<State, Transition> getTransitionDetails() {
        return transitionDetails;
    }

    @AllArgsConstructor
    private static final class TransitionPredicate implements Predicate<Transition> {

        private final Event event;

        @Override
        public boolean test(Transition transition) {
            return transition.getEvent().equals(event);
        }
    }
}
