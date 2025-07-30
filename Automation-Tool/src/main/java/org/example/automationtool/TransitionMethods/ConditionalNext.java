package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;
import org.example.automationtool.main.TransitionContext;

/**
 * Move to the next FSM state depending on a binary condition
 */
public class ConditionalNext implements Next{

    // index of state on success
    private Next success;

    // index of state on failure
    private Next fail;


    public ConditionalNext(Next success, Next fail){
        this.success = success;
        this.fail = fail;
    }


    @Override
    public void next(Status code, TransitionContext pointer) {

        switch (code){
            case SUCCESS -> {
                success.next(code, pointer);
            }

            case FAILURE -> {
                fail.next(code, pointer);
            }

            case UNRESOLVED_ERROR -> {pointer.setPointer(-1);}

        }

    }

}
