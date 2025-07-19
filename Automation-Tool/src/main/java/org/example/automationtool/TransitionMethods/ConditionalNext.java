package org.example.automationtool.TransitionMethods;

import org.example.automationtool.Actions.Status;

/**
 * Move to the next FSM state depending on a binary condition
 */
public class ConditionalNext implements Next{

    // index of state on success
    private Next success;

    // index of state on failure
    private Next fail;


    public ConditionalNext(Next succ, Next fail){
        this.success = succ;
        this.fail = fail;
    }


    @Override
    public int next(Status code, int pointer) {

        switch (code){
            case SUCCESS -> {
                return success.next(code, pointer);
            }

            case FAILURE -> {
                return fail.next(code, pointer);
            }

            case UNRESOLVED_ERROR -> {return -1;}

        }

        return -1;

    }

}
