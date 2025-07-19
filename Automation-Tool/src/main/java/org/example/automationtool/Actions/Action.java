package org.example.automationtool.Actions;


import org.example.automationtool.TransitionMethods.Next;

/**
 * Allows state to perform some task
 */
public interface Action {



    Status run();

    Next getNext();

}
