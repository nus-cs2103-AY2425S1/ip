package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main window of the Duke application.
 * Controls the primary user interface.
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
    private AnchorPane mainPane;

    private Duke duke;
    private Stage stage;
    private final Image userImage = new Image(this.getClass().getResource("/images/DaUser.png").toExternalForm());
    private final Image dukeImage = new Image(this.getClass().getResource("/images/DaDuke.png").toExternalForm());
    private boolean isDarkMode = false;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays Duke's introductory greeting.
     */
    @FXML
    public void sendIntro() {
        addDukeMessage(duke.getGreeting());
    }

    /**
     * Toggles dark mode for the main pane.
     */
    @FXML
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        mainPane.getStyleClass().removeIf(style -> style.equals("dark-mode"));
        if (isDarkMode) {
            mainPane.getStyleClass().add("dark-mode");
        }
    }

    /**
     * Sets the Duke instance.
     *
     * @param d The Duke instance to be used
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the Stage instance.
     *
     * @param s The Stage instance to be used
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Handles user input when the send button is clicked or Enter is pressed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            addUserMessage(input);
            String response = duke.getResponse(input);
            addDukeMessage(response);
            userInput.clear();
        }
    }

    /**
     * Adds a user message to the dialog container.
     *
     * @param message The user's message
     */
    private void addUserMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    /**
     * Adds a Duke message to the dialog container.
     *
     * @param message Duke's response message
     */
    private void addDukeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }
}