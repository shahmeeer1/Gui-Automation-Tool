package org.example.automationtool.Actions.move;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;

public class DirectedMoveTask extends MoveTask {

    private final int startingX;
    private final int startingY;

    protected DirectedMoveTask(int startingX, int startingY, int endingX, int endingY, Next next) {
        super(endingX, endingY, next);

        this.startingX = startingX;
        this.startingY = startingY;

        value.set(String.format("Move from (%d, %d) to (%d, %d)", startingX, startingY, endingX, endingY));
    }

    //TODO: Add delay or gliding effect
    @Override
    public Status run() {
        rb.mouseMove(startingX, startingY);
        rb.mouseMove(endingX, endingY);


        return Status.SUCCESS;
    }


}
