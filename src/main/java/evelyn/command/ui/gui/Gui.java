package evelyn.command.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gui extends Application{
    private static final String DEFAULT_FILE_PATH = "src/main/data/evelyn.txt";
    private String filePath;

    public Gui(String filePath) {
        this.filePath = filePath;
    }

    public Gui() {
        this(DEFAULT_FILE_PATH);
    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage.
    }
}
