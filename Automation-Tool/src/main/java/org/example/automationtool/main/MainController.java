//TODO: Add import export of tapes
//TODO: Organise open popup method logics

package org.example.automationtool.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainController implements Initializable{

    @FXML
    private Button StartScriptButton;
    @FXML
    private Button StopScriptButton;
    @FXML
    private Button VariableInsertButton;
    @FXML
    private Button TimerButton;
    @FXML
    private Button ClickActionButton;
    @FXML
    private Button MoveActionButton;
    @FXML
    private Button ImageSearchActionButton;
    @FXML
    private TableView<Action> Table;
//    @FXML
//    private TableColumn<Action, String> Number_Column;
    @FXML
    private TableColumn<Action, String> Action_Column;
    @FXML
    private TableColumn<Action, String> Value_Column;
    @FXML
    private TableColumn<Action, String> Label_Column;
    @FXML
    private TableColumn<Action, String> Comment_Column;

    @FXML
    private MenuItem Menu_Edit_clear;

    private Tape tape;
    private Consumer<Action> addToTape;

    private StateMachine machine;

    private Stage primaryStage;



    @FXML // takes callback and scene
    protected void onClickActionButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/automationtool/MouseClickWindow.fxml"));

        openClickMousePopup(loader);

    }

    @FXML // takes callback and scene
    protected void onMouseMoveButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/automationtool/MouseMoveWindow.fxml"));

        openMoveMousePopup(loader);
    }

    @FXML // takes callback
    protected void onDelayButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/automationtool/DelayWindow.fxml"));

        openPopupWithCB(loader, () -> {
                    DelayWindowController controller = loader.getController();
                    controller.setCallback(addToTape);
                }
        );

    }

    @FXML
    protected void onStartButton(){

        machine.execute();

        ((Stage) TimerButton.getScene().getWindow()).toFront();

    }

    @FXML
    protected void clearScript(){
        tape.getTape().clear();
    }

    @FXML
    protected void onImageSearchButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/automationtool/FindImageWindow.fxml"));


        openImageSearchPopup(loader);


    }


    private void openPopup(FXMLLoader loader){
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage popupStage = new Stage();
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();

    }

    /**
     * Load popup window.
     * Send callback to popup controller.
     * Callback to add state to tape
     * @param loader
     */
    private void openPopupWithCB(FXMLLoader loader, Runnable cont){
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage popupStage = new Stage();

        cont.run();

        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    /**
     * Load the move mouse window
     * @param loader
     */
    private void openMoveMousePopup(FXMLLoader loader){
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        MoveWindowController controller = loader.getController();
        controller.setScene(scene); // scene available here
        controller.setCallback(addToTape);

        Stage popupStage = new Stage();

        popupStage.setScene(scene);
        popupStage.setTitle("Your Window Title");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    private void openClickMousePopup(FXMLLoader loader){
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        ClickWindowController controller = loader.getController();
        controller.setScene(scene); // scene available here
        controller.setCallback(addToTape);

        Stage popupStage = new Stage();

        popupStage.setScene(scene);
        popupStage.setTitle("Your Window Title");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    private void openImageSearchPopup(FXMLLoader loader){
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        FindImageWindowController controller = loader.getController();
        controller.setScene(scene); // scene available here
        controller.setCallback(addToTape);

        Stage popupStage = new Stage();

        popupStage.setScene(scene);
        popupStage.setTitle("Your Window Title");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }




    //TODO: Complete table configurations
    //TODO: Add numbers column
    //TODO: generalise table process to allow for new tapes to be imported
    //TODO: export from table
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create new tape object
        tape = new Tape();
        addToTape = (state) -> {addStateToTape(state);};

        machine = new StateMachine(tape);



        // Connect table to tape
        Table.setItems(tape.getTape());
        Table.setEditable(true);
        Action_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("action"));
        Value_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("value"));
        Label_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("label"));
        Comment_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("comment"));

        Table.getColumns().setAll(Action_Column, Value_Column, Label_Column, Comment_Column);

        Label_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        Comment_Column.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    private void addStateToTape(Action state){
        tape.addState(state);
    }
}
