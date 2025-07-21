package org.example.automationtool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.automationtool.main.ClickWindowController;
import org.example.automationtool.main.MoveWindowController;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Main window gui from FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MouseMoveWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        MoveWindowController controller = loader.getController();
        controller.setScene(scene); // scene available here

        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Window Title");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }}
