package org.example.automationtool.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.search.searchTaskFactory;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class FindImageWindowController implements Initializable {

    @FXML
    private Button LoadImageButton;
    @FXML
    private Button FindButton;
    @FXML
    private ImageView ImageDisplay;
    @FXML
    private Scene scene;

    //Check boxes
    @FXML
    private CheckBox WhenFoundActionCB;
    @FXML
    private CheckBox ClickActionCB;
    @FXML
    private CheckBox MoveActionCB;

    // Combo boxes
    @FXML
    private ComboBox<String> ButtonDD;
    @FXML
    private ComboBox<String> ClickLocationDD;
    @FXML
    private ComboBox<String> MoveLocationDD;
    @FXML
    private ComboBox<String> SuccessLabelDD;
    @FXML
    private ComboBox<String> FailLabelDD;


    private Consumer<Action> sendState;

    private String absPath = null;

    @FXML
    protected void onLoadImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select template Image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) scene.getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage); // use your Stage here

        if (selectedFile != null) {
            absPath = selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            ImageDisplay.setImage(image); // Assuming you have an ImageView
        }
        else{
            System.out.println("NULL");
        }
    }

    @FXML
    protected void onFindButton(){
        // Check if image is selected and next labels have been entered
        if("".equals(absPath) || !isFilled(SuccessLabelDD) || !isFilled(FailLabelDD)){return;}
        System.out.println("PASS CHECK ONE");

        // Check if 2 checkboxes are selected
        boolean clickCB = ClickActionCB.isSelected();
        boolean moveCB = MoveActionCB.isSelected();
        boolean clickOrMoveSelected = clickCB || moveCB;
        if(!WhenFoundActionCB.isSelected() || !clickOrMoveSelected){return;}

        // Check which checkbox is selected and see if its combo boxes have been selected
        Action task;
        if(clickCB){
            String button = ButtonDD.getValue();
            String clickLocation = ClickLocationDD.getValue();
            if(button == null || clickLocation == null){return;}
            //TODO: Create image search object
            task = searchTaskFactory.CreateSearchTask(absPath, SuccessLabelDD.getValue(), FailLabelDD.getValue(), button, clickLocation);
        }
        else{
            // if move cb is selected
            String moveLocation = MoveLocationDD.getValue();
            if(moveLocation == null){return;}
            //TODO: Create image search object
            task = searchTaskFactory.CreateSearchTask(absPath, SuccessLabelDD.getValue(), FailLabelDD.getValue(), moveLocation);
        }

        sendState.accept(task);
    }

    private boolean isFilled(ComboBox<String> cb) {
        if (cb.isDisabled()) return false;
        String val = cb.getValue();
        return val != null && !val.isBlank();
    }


    private void setToggles(CheckBox box){
        String id = box.getId();

        switch(id){
            case "ClickActionCB" -> {
                MoveActionCB.setSelected(false);
                flipState(MoveActionCB);
            }
            case "MoveActionCB" -> {
                ClickActionCB.setSelected(false);
                flipState(ClickActionCB);
            }
        }
    }

    private void flipState(Control uiElem){
        uiElem.setDisable(!uiElem.isDisable());
    }

    public void setScene(Scene scene) {
        this.scene= scene;
        scene.addEventHandler(ActionEvent.ACTION, event ->{
            if(event.getTarget() instanceof CheckBox box){
                setToggles(box);
            }
        } );
    }

    public void setCallback(Consumer<Action> addToTape) {this.sendState = addToTape;
    }

    private void clearOnDisable(ComboBox<?> comboBox) {
        comboBox.disableProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) comboBox.setValue(null);
        });
    }

    private void addDDItems(ComboBox<String> comboBox, String... args){
        comboBox.getItems().addAll(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WhenFoundActionCB.selectedProperty().addListener((obs, oldVal, newVal) ->{
            ClickActionCB.setSelected(false);
            ClickActionCB.setDisable(!newVal);
            MoveActionCB.setSelected(false);
            MoveActionCB.setDisable(!newVal);
        });
        // Bind click action dropdowns to checkbox
        ButtonDD.disableProperty().bind(ClickActionCB.selectedProperty().not());
        ClickLocationDD.disableProperty().bind(ClickActionCB.selectedProperty().not());

        // Bind move action dropdown to checkbox
        MoveLocationDD.disableProperty().bind(MoveActionCB.selectedProperty().not());

        // Set combo boxes to clear when disabled
        clearOnDisable(ButtonDD);
        clearOnDisable(ClickLocationDD);
        clearOnDisable(MoveLocationDD);

        // Add comboBox options
        addDDItems(ButtonDD, "Left Click", "Right Click", "Scroll Click");
        addDDItems(ClickLocationDD, "Top Left", "Center");
        addDDItems(MoveLocationDD, "Top Left", "Center");
        addDDItems(SuccessLabelDD, "Next", "End");
        addDDItems(FailLabelDD, "Next", "End");


        SuccessLabelDD.setEditable(true);
        FailLabelDD.setEditable(true);
    }
}
