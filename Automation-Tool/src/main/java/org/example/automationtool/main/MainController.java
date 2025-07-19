package org.example.automationtool.main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.clickTaskFactory;
import org.example.automationtool.TransitionMethods.IncrementNext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private Button StartScriptButton;
    @FXML
    private Button StopScriptButton;
    @FXML
    private Button VariableInsertButton;
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

    private Tape tape;



    @FXML
    protected void onClickActionButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/automationtool/MouseClickWindow.fxml"));

        openPopup(loader);

        //TEST
        //Action n = clickTaskFactory.createClickTask(10, 20);
//        tape.addState(clickTaskFactory.createClickTask(10, 20));
//        tape.addState(clickTaskFactory.createClickTask(190, 30));


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

    //TODO: Complete table configurations
    //TODO: Add numbers column
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tape = new Tape();


        Table.setItems(tape.getTape());
        Action_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("action"));
        Value_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("value"));
        Label_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("label"));
        Comment_Column.setCellValueFactory(new PropertyValueFactory<Action, String>("comment"));

        Value_Column.setSortable(false);

        Table.getColumns().setAll(Action_Column, Value_Column, Label_Column, Comment_Column);

    }
}
