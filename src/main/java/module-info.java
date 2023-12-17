module project.parkingmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens project.parkingmanagement.Classes to javafx.base;
    opens project.parkingmanagement to javafx.fxml;
    exports project.parkingmanagement;
}