module org.example.automationtool {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.example.automationtool.TransitionMethods;
    opens org.example.automationtool.TransitionMethods to javafx.fxml;
    exports org.example.automationtool.Actions;
    opens org.example.automationtool.Actions to javafx.fxml;
    exports org.example.automationtool.main;
    opens org.example.automationtool.main to javafx.fxml;
    exports org.example.automationtool;
    opens org.example.automationtool to javafx.fxml;
}