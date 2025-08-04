package org.example.automationtool.Actions.search;

import javafx.util.Pair;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.Actions.move.MoveTask;
import org.example.automationtool.Actions.move.MoveTaskFactory;
import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.opencv.core.Point;

public class SearchAndMoveTask extends SearchTask implements Action {

    private String location;

    protected SearchAndMoveTask(String templatePath, ConditionalNext next, String location) {
        super(templatePath, next);
        this.location = location;
    }

    public Status run(){

        Pair<Double, Point> result;

        if(location.equals("Top Left")){
            result = ImageProcessor.DetectImg(template);
        }
        else{
            result = ImageProcessor.DetectImgCenter(template);
        }

        if(result.getKey() < 0.85){return Status.FAILURE;}

        int x = (int) result.getValue().x;
        int y = (int) result.getValue().y;

        MoveTask act = MoveTaskFactory.createMoveTask(x, y);
        act.run();

        return Status.SUCCESS;
    }

}
