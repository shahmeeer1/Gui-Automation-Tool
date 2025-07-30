package org.example.automationtool.main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;

import java.io.File;
import java.util.function.Consumer;

public class FindImageWindowController {

    @FXML
    private Button LoadImageButton;
    @FXML
    private Button FindButton;
    @FXML
    private ImageView ImageDisplay;
    @FXML
    private Scene scene;

    private Consumer<Action> sendState;

    @FXML
    protected void onLoadImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select template Image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) scene.getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage); // use your Stage here
        System.out.println(selectedFile.getAbsolutePath());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ImageDisplay.setImage(image); // Assuming you have an ImageView
        }
    }

    @FXML
    protected void onFindButton(){
        //if(ImageDisplay.is)
    }

    public void setScene(Scene scene) {this.scene= scene;
    }

    public void setCallback(Consumer<Action> addToTape) {this.sendState = addToTape;
    }
}
