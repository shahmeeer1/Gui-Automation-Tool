package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.main.TransitionContext;

/**
 * Move to any position on the FSM tape next.
 */
public class JumpNext implements Next{

    private String label;

    public JumpNext(String label){
        this.label=label;
    }

    public void setLabel(String label){this.label = label;}

    @Override
    public void next(Status code, TransitionContext pointer) {
        if(label.equals("End")){pointer.setPointer(-1); return;}
        int val = pointer.getLabelAddr(label);
        pointer.setPointer(val);
    }
}
