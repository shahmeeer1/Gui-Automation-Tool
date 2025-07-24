package org.example.automationtool.Actions.click;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;
import org.example.automationtool.main.RobotProvider;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class clickTask extends ActionWrapper implements Action {

    private static Robot rb = RobotProvider.getRobot();


    private static int getButton(String buttonName){
        return switch (buttonName) {
            case "Left Click" -> InputEvent.BUTTON1_DOWN_MASK;
            case "Right Click" -> InputEvent.BUTTON3_DOWN_MASK;
            case "Scroll Click" -> InputEvent.BUTTON2_DOWN_MASK;
            default -> -1;
        };
    }

    private int x;
    private int y;
    private int button;
    private final Next nextStrategy;

    protected clickTask(int x, int y, String button, Next next){
        super("Mouse Click");
        this.x = x;
        this.y = y;
        this.nextStrategy = next;
        this.button = getButton(button);


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
