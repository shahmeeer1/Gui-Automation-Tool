package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.Next;

public class clickTask extends ActionWrapper implements Action{

    private int x;
    private int y;
    private final Next nextStrategy;


    protected clickTask(int x, int y, Next next){
        super("Mouse Click");
        this.x = x;
        this.y = y;
        this.nextStrategy = next;


        value.set(String.format("(%d, %d)", x, y));
    }


    @Override
    public Status run() {
        // click at specified location
        return null;
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
