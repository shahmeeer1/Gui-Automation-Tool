package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class clickTaskFactory {

    public static MoveTask createClickTask(int x, int y){
        return new MoveTask(x, y, new IncrementNext());
    }
}
