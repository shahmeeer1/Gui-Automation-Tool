//TODO: use lambda in openpopup method
//TODO: set toggle logic for this window

package org.example.automationtool.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class MoveWindowController implements Initializable {

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
    private Scene scene;


    @FXML
    private void startingPosCBSwitch(ActionEvent event){
        //System.out.println(event.getTarget().equals(MoveCursorCB));

        // When selected, enable startingpos items
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
                entry.setTextFormatter(DigitFilter.createDigitFilter());
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
}
