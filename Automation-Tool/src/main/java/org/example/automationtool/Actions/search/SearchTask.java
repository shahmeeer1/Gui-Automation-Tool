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
import org.example.automationtool.Actions.click.clickTask;
import org.example.automationtool.Actions.click.clickTaskFactory;
import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.example.automationtool.TransitionMethods.IncrementNext;
import org.example.automationtool.TransitionMethods.Next;

import org.opencv.core.Point;

/**
 * Search for an image within an image.
 * Implements openCV template Matching.
 * Template Matching implementation Incomplete
 */
public class SearchTask extends ActionWrapper implements Action {

    private String template;
    private final ConditionalNext nextStrategy;

    protected SearchTask(String templatePath, ConditionalNext next){
        super("Find Image");
        this.template = templatePath;
        this.nextStrategy = next;

        value.set("");
    }


    @Override
    public Status run() {
        // delete
        //long startTime = System.nanoTime();

        // Get result of image search
        Pair<Double, Point> result = ImageProcessor.DetectImg(template);

        // TODO: allow user to specify threshold
        System.out.println("About to click");
        if(result.getKey() < 0.85){return Status.FAILURE;}
        // temporarily click at location
        int x = (int) result.getValue().x;
        int y = (int) result.getValue().y;


        clickTask act = clickTaskFactory.createClickTask(x, y, "Left Click");
        act.run();
        //DELETE:
//        long endTime = System.nanoTime();
//        long durationInNano = endTime - startTime;
//
//        System.out.println("Duration (nanoseconds): " + durationInNano);
//        System.out.println("Duration (milliseconds): " + (durationInNano / 1_000_000));


        return Status.SUCCESS;
    }

    @Override
    public Next getNext() {
        // TODO: Implement conditional next
        //return nextStrategy;
        return new IncrementNext();
    }
}
