module linktracker {
    requires javafx.controls;
    requires javafx.fxml;


    exports linktracker;
    opens linktracker to javafx.fxml;
    exports linktracker.utils;
    opens linktracker.utils to javafx.fxml;
}