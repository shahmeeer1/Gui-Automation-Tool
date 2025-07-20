package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class clickTaskFactory {

    public static clickTask createClickTask(int x, int y){
        return new clickTask(x, y, new IncrementNext());
    }
}
