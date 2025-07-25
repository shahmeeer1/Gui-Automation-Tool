package org.example.automationtool.Actions.click;

import org.example.automationtool.TransitionMethods.IncrementNext;

public class clickTaskFactory {

    private static final IncrementNext incrementer = new IncrementNext();

    public static clickTask createClickTask(int x, int y, String button){
        return new ClickAtGivenPos(x, y, button, incrementer);
    }

    public static clickTask createClickTask(String button){
        return new ClickCurrentPos(button, incrementer);
    }
}
