package org.example.automationtool.Actions.click;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;
import org.example.automationtool.main.RobotProvider;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class clickTask extends ActionWrapper implements Action {

    protected static Robot rb = RobotProvider.getRobot();
    protected int button;
    protected final Next nextStrategy;

    protected clickTask(String button, Next next){
        super("Mouse Click");
        this.nextStrategy = next;
        this.button = getButton(button);
        value.set("");
    }

    protected static int getButton(String buttonName){
        return switch (buttonName) {
            case "Left Click" -> InputEvent.BUTTON1_DOWN_MASK;
            case "Right Click" -> InputEvent.BUTTON3_DOWN_MASK;
            case "Scroll Click" -> InputEvent.BUTTON2_DOWN_MASK;
            default -> -1;
        };
    }

    @Override
    public Status run() {
        return null;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }

    //TEMPORARY
    public String toString(){
        return"Mouse click task";
    }
}
