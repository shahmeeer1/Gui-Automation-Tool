package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class MoveTaskFactory {

    public static MoveTask createMoveTask(int x, int y){
        return new MoveTask(x, y, new IncrementNext());
    }

}
