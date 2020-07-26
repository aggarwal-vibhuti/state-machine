package com.fsm.services;


import com.fsm.models.State;

import java.util.Collection;
import java.util.Set;

public interface StateManagementService {

    State getFrom();

    void setFrom(State state);

    void addEndStates(Collection<State> endStates);

    Set<State> getEndStates();

    Set<State> getReferenceStates();
}
