package org.example.automationtool.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController extends Application{

    private Tape tape;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Main window gui from FXML

        Parent root = FXMLLoader.load(getClass().getResource("/org/example/automationtool/MainWindow.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Automation Tool");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
