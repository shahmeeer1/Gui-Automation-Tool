package org.example.automationtool.main;

import javafx.collections.ObservableList;
import org.example.automationtool.Actions.Action;
import org.example.automationtool.Actions.Status;
import org.example.automationtool.TransitionMethods.Next;


public class StateMachine {

    private Tape tape;

    public StateMachine(Tape tape){
        this.tape = tape;
    }


    /**
     * Executes the tape currently loaded in the state machine.
     * @return true if completes uninterrupted execution, otherwise false.
     */
    public void execute(){
        // initialise script for execution
        ObservableList<Action> script = tape.getTape();

        TransitionContext pointer = new TransitionContext();
        pointer.setLabelMap(tape.findLabels());
        int pval;

        while((pval = pointer.getPointer()) < script.size()){

            Action state = script.get(pval);

            Status code = state.run();

            if(code != Status.SUCCESS){return;}//return false;}

            Next transition = state.getNext();

            transition.next(code, pointer);

        }
        System.out.println("Finished execution");

    }



    public void setTape(Tape tape) {
        this.tape = tape;
    }
}
