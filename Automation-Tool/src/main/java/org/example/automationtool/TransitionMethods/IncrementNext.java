package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.main.TransitionContext;

/**
 * Move to the next state on the FSM tape.
 */
public class IncrementNext implements Next{


    @Override
    public void next(Status code, TransitionContext pointer) {
        pointer.incrementPointer();
    }
}
