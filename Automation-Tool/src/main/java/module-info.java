module org.example.automationtool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires opencv;


    exports org.example.automationtool.TransitionMethods;
    opens org.example.automationtool.TransitionMethods to javafx.fxml;
    exports org.example.automationtool.Actions;
    opens org.example.automationtool.Actions to javafx.fxml;
    exports org.example.automationtool.main;
    opens org.example.automationtool.main to javafx.fxml;
    exports org.example.automationtool;
    opens org.example.automationtool to javafx.fxml;
    exports org.example.automationtool.Actions.click;
    opens org.example.automationtool.Actions.click to javafx.fxml;
    exports org.example.automationtool.Actions.move;
    opens org.example.automationtool.Actions.move to javafx.fxml;
    exports org.example.automationtool.Actions.delay;
    opens org.example.automationtool.Actions.delay to javafx.fxml;
    exports org.example.automationtool.Actions.search;
    opens org.example.automationtool.Actions.search to javafx.fxml;
}