package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.IncrementNext;
import org.example.automationtool.TransitionMethods.Next;

public class DelayTask extends ActionWrapper implements Action{

    private int delay; // delay in ms
    private final Next nextStrategy;

    protected DelayTask(int delay, Next next){
        super("Wait");
        this.delay = delay;
        nextStrategy = next;

        value.set(String.format("Wait for %d ms", delay));

    }

    @Override
    public Status run() {
        // Perform delay for specified number of seconds
        return null;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
