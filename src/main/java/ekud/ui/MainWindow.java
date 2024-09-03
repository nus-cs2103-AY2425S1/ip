package ekud.ui;

import ekud.Ekud;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 *
 * @author uniqly
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Scene scene;
    private Ekud ekud;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects a new {@link Ekud} instance with graphical UI.
     */
    public void setEkud() {
        GraphicalUi ui = new GraphicalUi(dialogContainer, this::readInput);
        ekud = new Ekud(ui);
        ekud.runStartup();
    }

    /**
     * Reads user input.
     */
    private String readInput() {
        String input = userInput.getText();
        userInput.clear();
        return input;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        ekud.runReadAndExecute();
        // terminates on "exit" or "bye" command
        if (ekud.isExited()) {
            ekud.runExit();
            Platform.exit(); // closes application
            System.exit(0); // terminates JVM
        }
    }
}
