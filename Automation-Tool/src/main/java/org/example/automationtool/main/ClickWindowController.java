//TODO: Consider creating interface to generalise controllers such as callback process
//TODO: Select button functionality
//TODO: Refactor text filter to class
package org.example.automationtool.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.click.clickTask;
import org.example.automationtool.Actions.click.clickTaskFactory;

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

    private Consumer<Action> sendState; //To send new state back ot main controller

    @FXML
    // Only enable confirm button if there is something in both entries
    protected void activate(KeyEvent event){
        if(xEntry.getLength() == 0 || yEntry.getLength() == 0){ClickButton.setDisable(true);}
        else{ClickButton.setDisable(false);}

    }

    @FXML
    protected void onClickConfirmButton(ActionEvent event){
        // Get contents of entries
        // try to create object
        // close on success, show error message on failure

        int x = Integer.parseInt(xEntry.getText());
        int y = Integer.parseInt(yEntry.getText());
        String button = ButtonComboBox.getValue();

        clickTask action = clickTaskFactory.createClickTask(x, y, button);

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
}
