/**
 * TODO: modify search task factory to allow for different variations in action when image is found
 * TODO: Allow for mouse ot move or click at found image.
 * TODO: create multiple search task subclasses that implement this.
 */

package org.example.automationtool.Actions.search;

import javafx.util.Pair;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.example.automationtool.TransitionMethods.Next;

import org.opencv.core.Point;

/**
 * Search for an image within an image.
 * Implements openCV template Matching.
 * Template Matching implementation Incomplete
 */
public class SearchTask extends ActionWrapper implements Action {

    protected String template;
    protected final ConditionalNext nextStrategy;

    protected SearchTask(String templatePath, ConditionalNext next){
        super("Find Image");
        this.template = templatePath;
        this.nextStrategy = next;

        value.set("");
    }


    @Override
    public Status run() {return null;}

    @Override
    public Next getNext() {
        return nextStrategy;
    }
}
