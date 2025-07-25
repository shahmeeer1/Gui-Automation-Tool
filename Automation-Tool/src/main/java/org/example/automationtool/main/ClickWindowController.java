//TODO: Consider creating interface to generalise controllers such as callback process
//TODO: Select button functionality
//TODO: Refactor text filter to class
package org.example.automationtool.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.click.clickTask;
import org.example.automationtool.Actions.click.clickTaskFactory;

import java.awt.MouseInfo;
import java.awt.Point;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ClickWindowController implements Initializable {

    @FXML
    private TextField xEntry;
    @FXML
    private TextField yEntry;

    @FXML
    private Button SelectButton;
    @FXML
    private Button ClickButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<String> ButtonComboBox;

    @FXML
    private CheckBox CurrentPositionCB;

    private Consumer<Action> sendState; //To send new state back ot main controller

    private Scene scene;

    @FXML
    // Only enable confirm button if there is something in both entries
    protected void activate(){
        if(xEntry.getLength() == 0 || yEntry.getLength() == 0 ){ClickButton.setDisable(true);}
        else{ClickButton.setDisable(false);}

    }

    @FXML
    // On select, disable x and y coord boxes. on disable, enable them
    protected void onCurrentPosCB(ActionEvent event){
        if(CurrentPositionCB.isSelected()){
            // Check box is selected
            xEntry.clear();
            xEntry.setDisable(true);
            yEntry.clear();
            yEntry.setDisable(true);
            ClickButton.setDisable(false);
        }
        else{
            xEntry.setDisable(false);
            yEntry.setDisable(false);
            ClickButton.setDisable(true);
        }
    }

    @FXML
    protected void onClickConfirmButton(ActionEvent event){
        // Get contents of entries
        // try to create object
        // close on success, show error message on failure

        String button = ButtonComboBox.getValue();
        clickTask action;

        if(CurrentPositionCB.isSelected()){
            action = clickTaskFactory.createClickTask(button);
        }
        else {
            int x = Integer.parseInt(xEntry.getText());
            int y = Integer.parseInt(yEntry.getText());

            action = clickTaskFactory.createClickTask(x, y, button);
        }

        //TODO: send back to main controller
        sendState.accept(action);

        //close window
        ((Stage) ClickButton.getScene().getWindow()).close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        xEntry.setTextFormatter(DigitFilter.createDigitFilter(5));
        yEntry.setTextFormatter(DigitFilter.createDigitFilter(5));

        ButtonComboBox.getItems().addAll("Left Click", "Right Click", "Scroll Click");
        ButtonComboBox.setValue("Left Click");
    }

    public void setCallback(Consumer<Action> callback) {
        this.sendState = callback;
    }

    /**
     * When space key is clicked, fill x and y coordinate
     * entries with respective mouse coordinates at moment of click
     * @param scene
     */
    public void setScene(Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if(event.getCode() == KeyCode.SPACE){

                Point p = MouseInfo.getPointerInfo().getLocation();

                xEntry.setText(String.valueOf(p.x));
                xEntry.end();
                yEntry.setText(String.valueOf(p.y));
                yEntry.end();
            }
        });


    }
}
