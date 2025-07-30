package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.main.TransitionContext;

/**
 * Update the pointer to the subsequent state.
 */
public interface Next {

    void next(Status code, TransitionContext pointer);

}
