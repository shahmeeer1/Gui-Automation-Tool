package org.example.automationtool.Actions.delay;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class DelayTaskFactory {

    public static DelayTask createDelayTask(int delay){
        return new DelayTask(delay, new IncrementNext());
    }
}
