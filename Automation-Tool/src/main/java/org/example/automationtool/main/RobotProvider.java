package org.example.automationtool.main;
import java.awt.Robot;

/**
 * Returns an instance of the Robot class to caller.
 * To be used for automating actions.
 */
public class RobotProvider {

    private static Robot robot;

    static {
        try{
            robot = new Robot();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Robot getRobot(){
        return robot;
    }

    private RobotProvider(){}


}
