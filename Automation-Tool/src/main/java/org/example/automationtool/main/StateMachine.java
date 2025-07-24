package org.example.automationtool.main;

import javafx.collections.ObservableList;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;

import java.util.ArrayList;

public class StateMachine {

    private ObservableList<Action> tape;

    public StateMachine(ObservableList<Action> tape){
        this.tape = tape;
    }


    /**
     * Executes the tape currently loaded in the state machine.
     * @return true if completes uninterrupted execution, otherwise false.
     */
    public boolean execute(){

        for(int pointer = 0; pointer < tape.size();){
            Action state = tape.get(pointer);

            Status code = state.run();

            if(code != Status.SUCCESS){return false;}

            Next transition = state.getNext();

            pointer = transition.next(code, pointer);

        }
        System.out.println("Finished execution");
        return true;

    }


    public void setTape(ObservableList<Action> tape) {
        this.tape = tape;
    }
}
