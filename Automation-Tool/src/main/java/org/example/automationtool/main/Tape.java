package org.example.automationtool.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.ActionWrapper;

import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Populate hashmap with labels and their position in the script
     * @return - hashmap containing labels and positions in the script
     */
    public HashMap<String, Integer> findLabels(){
        HashMap<String, Integer> labelMap = new HashMap<>();

        for(int i = 0; i < tape.size(); i++){
            ActionWrapper action = (ActionWrapper) tape.get(i);
            if(!(action.getLabel().equals("-"))){
                labelMap.put(action.getLabel(), i);
            }
        }
        return labelMap;
    }
}
