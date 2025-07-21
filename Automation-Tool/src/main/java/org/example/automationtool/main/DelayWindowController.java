package org.example.automationtool.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.DelayTask;
import org.example.automationtool.Actions.DelayTaskFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class DelayWindowController implements Initializable {

    @FXML
    private TextField DelayEntry;
    @FXML
    private Button ConfirmDelayButton;
    @FXML
    private Label ErrorLabel;

    private Consumer<Action> sendState;

    @FXML
    protected void onConfirmButtonClick(){
        // get data from entry
        Integer val = toInt(DelayEntry.getText());
        //Check if interval is valid
        if(val < 100){displayError("Minimum delay: 100 ms");return;}
        // create delay object
        DelayTask state = DelayTaskFactory.createDelayTask(val);
        //send to controller
        sendState.accept(state);
        //close window
        ((Stage) ConfirmDelayButton.getScene().getWindow()).close();
    }

    private Integer toInt(String val){
        if(val.isEmpty()){return 0;}
        else{
            return Integer.parseInt(val);
        }
    }

    private void displayError(String message){
        ErrorLabel.setText(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DelayEntry.setTextFormatter(DigitFilter.createDigitFilter(7));
    }

    public void setCallback(Consumer<Action> callback){
        this.sendState = callback;
    }
}
