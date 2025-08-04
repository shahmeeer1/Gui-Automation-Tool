package org.example.automationtool.Actions.search;

import org.example.automationtool.TransitionMethods.ConditionalNext;


// TODO: Complete conditional next implementation
public class searchTaskFactory {

    public static SearchTask CreateSearchTask(String template, String successLabel, String failureLabel, String button, String clickLocation){
        return new searchAndClickTask(template, new ConditionalNext(successLabel, failureLabel), button, clickLocation);

    }
    public static SearchTask CreateSearchTask(String template, String successLabel, String failureLabel, String moveLocation){
        return new SearchAndMoveTask(template, new ConditionalNext(successLabel, failureLabel), moveLocation);
    }


}
