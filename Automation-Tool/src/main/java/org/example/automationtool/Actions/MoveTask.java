package org.example.automationtool.Actions;


import org.example.automationtool.TransitionMethods.Next;

/**
 * Move The mouse to a given set of coordinates on the screen.
 * Implements Java Robot api.
 * Mouse movement implementation incomplete-
 */
public class MoveTask implements Action{
    private final int x;
    private final int y;
    private final Next nextStrategy;

    public MoveTask(int x, int y, Next next){
        this.x = x;
        this.y = y;
        this.nextStrategy = next;
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
