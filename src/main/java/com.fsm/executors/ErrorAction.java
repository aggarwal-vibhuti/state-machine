package com.fsm.executors;


import com.fsm.entities.Context;
import com.fsm.exceptions.FSMRunTimeException;

public interface ErrorAction<C extends Context> extends Action {

    void call(FSMRunTimeException error, C context);
}
