package org.example.automationtool.Actions.search;

import javafx.util.Pair;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.Actions.click.clickTask;
import org.example.automationtool.Actions.click.clickTaskFactory;
import org.example.automationtool.TransitionMethods.ConditionalNext;
import org.opencv.core.Point;

public class searchAndClickTask extends SearchTask implements Action {

    private String button;
    private String location;

    protected searchAndClickTask(String templatePath, ConditionalNext next, String button, String location) {
        super(templatePath, next);
        this.button = button;
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

        clickTask act = clickTaskFactory.createClickTask(x, y, button);
        act.run();

        return Status.SUCCESS;
    }


}
