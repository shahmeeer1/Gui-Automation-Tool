package org.example.automationtool.Actions;


import org.example.automationtool.TransitionMethods.Next;

/**
 * Move The mouse to a given set of coordinates on the screen.
 * Implements Java Robot api.
 * Mouse movement implementation incomplete-
 */
public class MoveTask extends ActionWrapper implements Action{
    private final int x;
    private final int y;
    private final Next nextStrategy;

    protected MoveTask(int x, int y, Next next){
        super("Move Mouse");
        this.x = x;
        this.y = y;
        this.nextStrategy = next;


        value.set(String.format("Move to (%d, %d)", x, y));
    }

    @Override
    public Status run() {
        System.out.printf("moving to (%d, %d)%n", x, y);
        return Status.SUCCESS;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
