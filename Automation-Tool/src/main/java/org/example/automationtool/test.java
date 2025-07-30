package org.example.automationtool;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.automationtool.Actions.search.SearchTask;
import org.example.automationtool.Actions.search.searchTaskFactory;


public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        // Load Main window gui from FXML
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("MouseClickWindow.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//
//        ClickWindowController controller = loader.getController();
//        controller.setCallback(null); // scene available here
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Your Window Title");
//        primaryStage.show();
    }

    public static void main(String[] args) {

        SearchTask test1 = searchTaskFactory.CreateSearchTask("C:\\Users\\shahmeer\\Documents\\GitHub\\Gui-Automation-Tool\\Automation-Tool\\src\\main\\resources\\icons\\searchtestimg1.png");
        test1.run();

        //launch(args);

    }}
