package com.fsm;

import com.fsm.entities.Context;
import com.fsm.entities.EventType;
import com.fsm.entities.Transition;
import com.fsm.executors.ErrorAction;
import com.fsm.executors.EventAction;
import com.fsm.exceptions.FSMRunTimeException;
import com.fsm.exceptions.InvalidTransitionException;
import com.fsm.models.Event;
import com.fsm.models.State;
import com.fsm.services.ActionService;
import com.fsm.services.StateManagementService;
import com.fsm.services.TransitionService;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class StateMachine<C extends Context> {

    private final TransitionService transitionService;
    private final StateManagementService stateManagementService;
    private final ActionService actionService;

    public StateMachine(
            State startState,
            TransitionService transitionService,
            StateManagementService stateManagementService,
            ActionService actionService) {
        this.transitionService = transitionService;
        this.stateManagementService = stateManagementService;
        this.actionService = actionService;
        this.stateManagementService.setFrom(startState);
        this.actionService.setHandler(EventType.ERROR, null, null, new DefaultErrorAction());
    }

    public void addTransition(State state, Transition transition) {
        transitionService.addTransition(state, transition);
    }

    public void addEndStates(Collection<State> endStates) {
        stateManagementService.addEndStates(endStates);
    }

    public Optional<Transition> getTransition(State from, Event event) {
        return transitionService.getTransition(from, event);
    }

    public Set<Transition> getAllowedTransitions(State from) {
        Multimap<State, Transition> allTransitions = transitionService.getTransitionDetails();
        return (Set<Transition>) allTransitions.get(from);
    }

    public void fire(final Event event, final C context) {
        final State from = context.getFrom();
        final Optional<Transition> transition = getTransition(from, event);
        if (!transition.isPresent()) {
            throw new InvalidTransitionException(
                    "Invalid Event: " + event + " triggered while in State: " + context.getFrom() + " for " + context);
        }
        State to = transition.get().getTo();
        context.setTo(to);
        actionService.handleTransition(context);
    }

    public <C extends Context> StateMachine<C> onTransition(EventAction<C> transition) {
        actionService.onTransition(transition);
        return (StateMachine<C>) this;
    }

    public static class DefaultErrorAction implements ErrorAction<Context> {

        @Override
        public void call(FSMRunTimeException error, Context context) {
            String errorMessage = "Runtime Error in state [" + error.getState() + "]";
            if (!Objects.isNull(error.getEvent())) {
                errorMessage += "on Event [" + error.getEvent() + "]";
            }
            errorMessage += "with context [" + error.getContext() + "]";
            log.error("ERROR", new Exception(errorMessage, error));
        }
    }
}
