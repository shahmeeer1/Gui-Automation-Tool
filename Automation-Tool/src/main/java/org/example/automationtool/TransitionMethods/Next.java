package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;

/**
 * Update the pointer to the subsequent state.
 */
public interface Next {

    int next(Status code, int pointer);

}
