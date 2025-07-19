package org.example.automationtool;

import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;

import java.util.ArrayList;

public class StateMachine {

    private ArrayList<Action> tape;

    public StateMachine(ArrayList<Action> tape){
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

        return true;

    }


    public void setTape(ArrayList<Action> tape) {
        this.tape = tape;
    }
}
