module chatbaby {
    requires javafx.controls;
    requires javafx.fxml;

    // Export the chatbaby package to allow access from JavaFX
    exports chatbaby to javafx.graphics;

    // Open chatbaby to allow access from FXML
    opens chatbaby to javafx.fxml;
}
