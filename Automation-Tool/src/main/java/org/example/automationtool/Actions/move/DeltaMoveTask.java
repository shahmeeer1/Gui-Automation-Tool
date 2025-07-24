//TODO: Should not inherit from MoveTask
//TODO: Must restructure this

package org.example.automationtool.Actions.move;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;

import java.awt.Point;
import java.awt.MouseInfo;


public class DeltaMoveTask extends MoveTask{


    protected DeltaMoveTask(int relativeX, int relativeY, Next next) {
        super(relativeX, relativeY, next);

        value.set(String.format("Move by (%d, %d)", relativeX, relativeY));
    }


    @Override
    public Status run() {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        int x = mousePos.x + endingX;
        int y = mousePos.y + endingY;
        rb.mouseMove(x, y);
        return Status.SUCCESS;
    }
}
