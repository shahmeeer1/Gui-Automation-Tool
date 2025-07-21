package org.example.automationtool.main;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class DigitFilter {

    private DigitFilter(){}

    public static TextFormatter<UnaryOperator<TextFormatter.Change>> createDigitFilter(){
        UnaryOperator<TextFormatter.Change> digitFilter = change ->{

            String newText = change.getControlNewText();

            if(newText.matches("\\d+") && newText.length() < 6){
                return change; // accept input
            }
            else if(change.isReplaced()){
                // reject unaccepted replacements
                return null;
            }
            else if (change.isDeleted()) {
                return change;
            }
            else{
                return null;} // reject change
        };

        return new TextFormatter<>(digitFilter);
    }
}
