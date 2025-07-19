package org.example.automationtool.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.automationtool.Actions.Action;

import java.util.ArrayList;

public class Tape {

    private ObservableList<Action> tape;

    public Tape(){
        tape = FXCollections.observableArrayList();
    }

    public ObservableList<Action> getTape(){
        return tape;
    }

    public void addState(Action state){
        tape.add(state);
    }

}
