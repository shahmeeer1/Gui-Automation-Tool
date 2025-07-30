package org.example.automationtool.main;

import java.util.HashMap;

public class TransitionContext {

    public TransitionContext(){
        pointer = 0;
    }

    private int pointer;
    private HashMap<String, Integer> labelMap;


    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    /**
     * looks up the given label.
     * @param label - name of the label to lookup.
     * @return label position in script, -1 if not found
     */
    public int getLabelAddr(String label) {
        return labelMap.getOrDefault(label, -1);
    }

    public void setLabelMap(HashMap<String, Integer> labelMap) {
        this.labelMap = labelMap;
    }

    public void incrementPointer(){pointer++;}
}
