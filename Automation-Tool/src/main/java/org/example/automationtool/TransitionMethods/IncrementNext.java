package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;

/**
 * Move to the next state on the FSM tape.
 */
public class IncrementNext implements Next{


    @Override
    public int next(Status code, int pointer) {
        return ++pointer;
    }
}
