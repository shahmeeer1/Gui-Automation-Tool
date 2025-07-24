package org.example.automationtool.Actions.move;


import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;
import org.example.automationtool.main.RobotProvider;

import java.awt.Robot;

/**
 * Move The mouse to a given set of coordinates on the screen.
 * Implements Java Robot api.
 * Mouse movement implementation incomplete-
 */
public class MoveTask extends ActionWrapper implements Action {

    protected static Robot rb = RobotProvider.getRobot();

    protected final int endingX;
    protected final int endingY;
    private final Next nextStrategy;

    protected MoveTask(int endingX, int endingY, Next next){
        super("Move Mouse");
        this.endingX = endingX;
        this.endingY = endingY;
        this.nextStrategy = next;

        value.set(String.format("Move to (%d, %d)", endingX, endingY));
    }


    @Override
    public Status run() {
        rb.mouseMove(endingX, endingY);

        return Status.SUCCESS;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
