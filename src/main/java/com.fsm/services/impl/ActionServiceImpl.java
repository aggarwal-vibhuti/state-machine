package com.fsm.services.impl;


import com.fsm.entities.Context;
import com.fsm.entities.EventType;
import com.fsm.entities.HandlerType;
import com.fsm.executors.Action;
import com.fsm.executors.EventAction;
import com.fsm.models.Event;
import com.fsm.models.State;
import com.fsm.services.ActionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActionServiceImpl implements ActionService {

    private final Map<HandlerType, Action> handlers;

    public ActionServiceImpl() {
        handlers = new HashMap<>();
    }

    @Override
    public <C extends Context> void onTransition(EventAction<C> context) {
        handlers.put(new HandlerType(EventType.ON_STATE_TRANSITION), context);
    }

    @Override
    public void setHandler(EventType eventType, State state, Event event, Action action) {
        handlers.put(new HandlerType(eventType, event, state), action);
    }

    @Override
    public <C extends Context> void handleTransition(C context) {
        Action h = handlers.get(new HandlerType(EventType.ON_STATE_TRANSITION));
        if (!Objects.isNull(h) && h instanceof EventAction) {
            ((EventAction<C>) h).call(context);
        }
    }
}
