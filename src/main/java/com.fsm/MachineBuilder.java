package com.fsm;


import com.fsm.entities.Context;
import com.fsm.entities.Transition;
import com.fsm.models.Event;
import com.fsm.models.State;
import com.fsm.services.ActionService;
import com.fsm.services.StateManagementService;
import com.fsm.services.TransitionService;
import com.fsm.services.impl.ActionServiceImpl;
import com.fsm.services.impl.StateManagementServiceImpl;
import com.fsm.services.impl.TransitionServiceImpl;

import java.util.Collection;

public class MachineBuilder<C extends Context> {

    private final StateMachine<C> stateMachine;

    private MachineBuilder(
            State startState,
            TransitionService transitionService,
            StateManagementService stateManagementService,
            ActionService actionService) {
        stateMachine =
                new StateMachine<>(startState, transitionService, stateManagementService, actionService);
    }

    public static <C extends Context> MachineBuilder<C> start(State startState) {
        return new MachineBuilder<>(
                startState,
                new TransitionServiceImpl(),
                new StateManagementServiceImpl(),
                new ActionServiceImpl());
    }

    public <C extends Context> MachineBuilder<C> addTransition(Event event, State from, State to) {
        stateMachine.addTransition(from, new Transition(event, from, to));
        return (MachineBuilder<C>) this;
    }

    public StateMachine<C> end(Collection<State> endStates) {
        stateMachine.addEndStates(endStates);
        return stateMachine;
    }
}
