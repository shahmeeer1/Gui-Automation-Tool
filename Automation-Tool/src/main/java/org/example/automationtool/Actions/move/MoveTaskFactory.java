package org.example.automationtool.Actions.move;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class MoveTaskFactory {

    private static final IncrementNext incrementer = new IncrementNext();

    // Move to position
    public static MoveTask createMoveTask(int x, int y){return new MoveTask(x, y, incrementer);}

    // Move relative to current position
    public static MoveTask createMoveTask(int x, int y, boolean delta){return new DeltaMoveTask(x, y, incrementer);}

    // Move from given starting position to end position
    public static MoveTask createMoveTask(int startingX, int startingY, int endX, int endY){return new DirectedMoveTask(startingX, startingY, endX, endY, incrementer);}

}
