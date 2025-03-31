module com.learnerapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.learnerapp.view to javafx.fxml;
    exports com.learnerapp;
    exports com.learnerapp.view;
}