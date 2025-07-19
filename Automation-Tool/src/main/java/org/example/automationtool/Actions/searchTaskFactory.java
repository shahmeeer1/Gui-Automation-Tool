package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.example.automationtool.TransitionMethods.IncrementNext;


// TODO: Complete conditional next implementation
public class searchTaskFactory {

    public static SearchTask CreateSearchTask(String template, String image){
        return new SearchTask(template, image, new ConditionalNext(null, null));
    }


}
