package com.fsm.services;


import com.fsm.entities.Context;
import com.fsm.entities.EventType;
import com.fsm.executors.Action;
import com.fsm.executors.EventAction;
import com.fsm.models.Event;
import com.fsm.models.State;

public interface ActionService {

    <C extends Context> void onTransition(EventAction<C> context);

    void setHandler(EventType eventType, State state, Event event, Action action);

    <C extends Context> void handleTransition(C context);
}
