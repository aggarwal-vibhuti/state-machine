package com.fsm.services.impl;

import com.fsm.models.State;
import com.fsm.services.StateManagementService;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

public class StateManagementServiceImpl implements StateManagementService {

    private final Set<State> endStates;
    private State from;

    public StateManagementServiceImpl() {
        endStates = Sets.newHashSet();
    }

    @Override
    public State getFrom() {
        return from;
    }

    @Override
    public void setFrom(State state) {
        this.from = state;
    }

    @Override
    public void addEndStates(Collection<State> endStates) {
        this.endStates.addAll(endStates);
    }

    @Override
    public Set<State> getEndStates() {
        return endStates;
    }

    @Override
    public Set<State> getReferenceStates() {
        Set<State> states = Sets.newHashSet(this.from);
        states.addAll(this.endStates);
        return states;
    }
}
