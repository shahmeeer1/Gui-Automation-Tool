package org.example.automationtool.Actions.click;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;

public class ClickAtGivenPos extends clickTask implements Action {

    private int x;
    private int y;

    protected ClickAtGivenPos(int x, int y, String button, Next next){
        super(button, next);
        this.x = x;
        this.y = y;

        value.set(String.format("(%d, %d)", x, y));
    }

    @Override
    public Status run() {
        rb.mouseMove(x, y);
        rb.mousePress(button);
        rb.mouseRelease(button);
        return Status.SUCCESS;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }

    //TEMPORARY
    public String toString(){
        return String.format("Clicking at (%d, %d)", x, y);
    }
}
