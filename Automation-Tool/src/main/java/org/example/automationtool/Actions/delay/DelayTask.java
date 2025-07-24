package org.example.automationtool.Actions.delay;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;
import org.example.automationtool.main.RobotProvider;

import java.awt.*;

public class DelayTask extends ActionWrapper implements Action {

    private int delay; // delay in ms
    private final Next nextStrategy;
    private static Robot rb = RobotProvider.getRobot();


    protected DelayTask(int delay, Next next){
        super("Wait");
        this.delay = delay;
        nextStrategy = next;

        value.set(String.format("Wait for %d ms", delay));

    }

    @Override
    public Status run() {
        rb.delay(delay);
        return Status.SUCCESS;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
