package org.example.automationtool.Actions.click;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;


public class ClickCurrentPos extends clickTask implements Action {

    protected ClickCurrentPos(String button, Next next){
        super(button, next);

        value.set("Clicking at cursor position");
    }

    @Override
    public Status run() {
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
        return "Clicking mouse and current position";
    }
}
