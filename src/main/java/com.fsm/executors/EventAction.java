package com.fsm.executors;


import com.fsm.entities.Context;

public interface EventAction<C extends Context> extends Action {

    void call(C context);
}
