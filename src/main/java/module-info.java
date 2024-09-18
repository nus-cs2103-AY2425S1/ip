module chatbaby {
    requires javafx.controls;
    requires javafx.fxml;

    // Export the chatbaby package to allow access from JavaFX
    exports chatbaby to javafx.graphics;

    // If you need to access classes with reflection in FXML, use this
    opens chatbaby to javafx.fxml;
}
