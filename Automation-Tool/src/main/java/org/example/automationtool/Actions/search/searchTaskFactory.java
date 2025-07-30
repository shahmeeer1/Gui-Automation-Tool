package org.example.automationtool.Actions.search;

import org.example.automationtool.TransitionMethods.ConditionalNext;


// TODO: Complete conditional next implementation
public class searchTaskFactory {

    public static SearchTask CreateSearchTask(String template){
        return new SearchTask(template, new ConditionalNext(null, null));
        //return null;
    }


}
