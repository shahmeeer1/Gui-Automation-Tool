// TODO: Disable confirm button when all checkboxes are unselected
// TODO: display errors messages on text labels when out of bounds input entered


package org.example.automationtool.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.move.MoveTask;
import org.example.automationtool.Actions.move.MoveTaskFactory;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class MoveWindowController implements Initializable {

    // Button
    @FXML
    private Button ConfirmButton;

    // Text Entries
    @FXML
    private TextField xEntry1;
    @FXML
    private TextField xEntry2;
    @FXML
    private TextField xEntry3;
    @FXML
    private TextField yEntry1;
    @FXML
    private TextField yEntry2;
    @FXML
    private TextField yEntry3;

    // Check Boxes
    @FXML
    private CheckBox MoveCursorCB;
    @FXML
    private CheckBox StartingPosCB;
    @FXML
    private CheckBox DeltaMoveCB;

    // control containers
    private TextField[] moveContainer;
    private TextField[] startContainer;
    private TextField[] deltaContainer;

    // CheckBox ID -> TextField hashmap
    private HashMap<CheckBox, TextField[]> CBToTextFieldMap;

    // Store scene for initialization;
    private Scene scene;

    private Consumer<Action> sendState;


    @FXML
    private void onConfirmButton(){

        Pair<Integer, Integer> moveTo;
        MoveTask state = null;

        // validate fields
        // Check what kind of movement is being made
        if(DeltaMoveCB.isSelected()){
            moveTo = getEntryData(DeltaMoveCB);
            state = MoveTaskFactory.createMoveTask(moveTo.getKey(), moveTo.getValue(), true);
        }
        else if(MoveCursorCB.isSelected()){
            if(StartingPosCB.isSelected()){

                // move with start object
                Pair<Integer, Integer> startAt;

                moveTo = getEntryData(MoveCursorCB);    // End position of cursor
                startAt = getEntryData(StartingPosCB);  // Starting position of cursor

                state = MoveTaskFactory.createMoveTask(
                        startAt.getKey(), startAt.getValue(),
                        moveTo.getKey(), moveTo.getValue()
                );
            }
            else{

                // only move cursor
                moveTo = getEntryData(MoveCursorCB);
                state = MoveTaskFactory.createMoveTask(moveTo.getKey(), moveTo.getValue());
            }
        }
        else{
            //TODO: Should display error on label.
            return;

        }
        // return objects to main

        sendState.accept(state);
        // close window

        ((Stage) scene.getWindow()).close();
    }

    /**
     * Retrieve x and y text field values for given
     * @param box
     * @return
     */
    private Pair<Integer, Integer> getEntryData(CheckBox box){

        TextField[] entryArr = CBToTextFieldMap.get(box);

        Integer xVal =  toInt(entryArr[0].getText());
        Integer yVal =  toInt(entryArr[1].getText());

        return new Pair<>(xVal, yVal);
    }

    private Integer toInt(String val){
        if(val.isEmpty()){return 0;}
        else{
            return Integer.parseInt(val);
        }
    }


    private void setToggles(CheckBox box){

        String id = box.getId();

        switch(id){
            case("MoveCursorCB"):{

                flipState(StartingPosCB);
                StartingPosCB.setSelected(false);
                flipState(DeltaMoveCB);

                toggleEntries(StartingPosCB);
                toggleEntries(DeltaMoveCB);
                toggleEntries(MoveCursorCB);
                break;

            }
            case("StartingPosCB"):{
                toggleEntries(StartingPosCB);
                break;
            }
            case("DeltaMoveCB"):{
                MoveCursorCB.setSelected(false);
                flipState(MoveCursorCB);

                toggleEntries(MoveCursorCB);
                toggleEntries(DeltaMoveCB);
                break;

            }
        }

    }

    private void flipState(Control uiElem){
        uiElem.setDisable(!uiElem.isDisable());
    }

    private void toggleEntries(CheckBox box){
        TextField[] entries = CBToTextFieldMap.get(box);
        boolean val = box.isDisable() || !box.isSelected();
        for(TextField ent: entries){
            ent.setDisable(val);
            ent.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveContainer = new TextField[] {xEntry1, yEntry1};
        startContainer = new TextField[] {xEntry2, yEntry2};
        deltaContainer = new TextField[] {xEntry3, yEntry3};

        CBToTextFieldMap = new HashMap<>();
        CBToTextFieldMap.put(MoveCursorCB, moveContainer);
        CBToTextFieldMap.put(StartingPosCB, startContainer);
        CBToTextFieldMap.put(DeltaMoveCB, deltaContainer);

        for(TextField[] entryArr:CBToTextFieldMap.values()){
            for(TextField entry: entryArr){
                entry.setTextFormatter(DigitFilter.createDigitFilter(5));
            }
        }


    }

    private void setCheckBoxListener(){
        scene.addEventHandler(ActionEvent.ACTION, event -> {

            if(event.getTarget() instanceof CheckBox box){
                setToggles(box);
            }
        });
    }

    public void setScene(Scene scene){
        this.scene = scene;

        setCheckBoxListener();
    }

    public void setCallback(Consumer<Action> callback) {
        this.sendState = callback;
    }
}
