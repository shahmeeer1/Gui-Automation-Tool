package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;

/**
 * Move to any position on the FSM tape next.
 */
public class JumpNext implements Next{
    private final int next;

    public JumpNext(int next){this.next=next;}

    @Override
    public int next(Status code, int pointer) {
        return next;
    }
}
