module Yu.Gi.Oh {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;


    opens view to javafx.fxml;
    exports view;
}