package org.example.automationtool.Actions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ActionWrapper {

    protected SimpleStringProperty action;
    protected SimpleStringProperty value;
    protected SimpleStringProperty label;
    protected SimpleStringProperty comment;

    protected ActionWrapper(String action){
        this.action = new SimpleStringProperty(action);
        System.out.println(this.action.get());

        this.label = new SimpleStringProperty("-");
        this.comment = new SimpleStringProperty("");
        this.value = new SimpleStringProperty();
    }

    public StringProperty actionProperty() { return action; }
    public StringProperty valueProperty() { return value; }
    public StringProperty labelProperty() { return label; }
    public StringProperty commentProperty() { return comment; }

    public String getAction() { return action.get(); }
    public String getValue() { return value.get(); }
    public String getLabel() { return label.get(); }
    public String getComment() { return comment.get(); }

    public void setAction(String action) { this.action.set(action); }
    public void setValue(String value) { this.value.set(value); }
    public void setLabel(String label) {this.label.set(label); }
    public void setComment(String comment) { this.comment.set(comment); }

}

