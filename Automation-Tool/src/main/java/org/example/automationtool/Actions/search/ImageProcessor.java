package org.example.automationtool.Actions.search;

import javafx.util.Pair;
import org.example.automationtool.main.RobotProvider;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageProcessor {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    private static final Robot rb = RobotProvider.getRobot();

    /**
     * Take a screenshot of the desktop and return it as a Mat
     * @return Mat screenshot of desktop
     */
    private static Mat ssDesktop(){
        BufferedImage desktopImg = rb.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        return biToMat(desktopImg);

    }

    // Generated from AI. need to validate function
    private static Mat biToMat(BufferedImage img){
        BufferedImage img2 = convertTo3ByteBGR(img);

        byte[] pixels = ((DataBufferByte) img2.getRaster().getDataBuffer()).getData();

        Mat mat = new Mat(img2.getHeight(), img2.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
    }

    public static BufferedImage convertTo3ByteBGR(BufferedImage image) {
        BufferedImage convertedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR
        );
        Graphics2D g = convertedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return convertedImage;
    }


    /**
     * Detect the given image on screen and return the result and location
     * @param templatePath - path of image to be detected.
     * @return pair containing max detected value and its location
     */
    public static Pair<Double, org.opencv.core.Point> DetectImg(String templatePath){
        // Get template and background image
        Mat img = Imgcodecs.imread(templatePath);
        Mat desktopImg = ssDesktop();

        // Create results matrix
        int resultCols = desktopImg.cols() - img.cols() + 1;
        int resultRows = desktopImg.rows() - img.rows() + 1;
        Mat results = new Mat(resultRows, resultCols, CvType.CV_32FC1);

        // Begin template matching process
        Imgproc.matchTemplate(desktopImg, img, results, Imgproc.TM_CCOEFF_NORMED);

        // Find max and minimum result
        Core.MinMaxLocResult mmr = Core.minMaxLoc(results);

        outputResults(mmr); // For debugging and testing

        return new Pair<>(mmr.maxVal, mmr.maxLoc);
    }

    private static void outputResults(Core.MinMaxLocResult mmr){
        System.out.println("Max value: " + mmr.maxVal);
        System.out.println("Max Location: " + mmr.maxLoc);
        System.out.println("Min value: " + mmr.minVal);
        System.out.println("Min Location: " + mmr.minLoc);

    }

}
