package org.example.automationtool.Actions;

import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.example.automationtool.TransitionMethods.Next;

/**
 * Search for an image within an image.
 * Implements openCV template Matching.
 * Template Matching implementation Incomplete
 */
public class SearchTask implements Action{

    private String template;
    private String image;
    private final ConditionalNext nextStrategy;

    public SearchTask(String image, String template, ConditionalNext next){
        this.image = image;
        this.template = template;
        this.nextStrategy = next;
    }


    @Override
    public Status run() {
        /**
         * Use open cv to template match.
         * If successful, call ConditionalNext.setTrue method
         */
        System.out.println("Found image");
        return null;
    }

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
