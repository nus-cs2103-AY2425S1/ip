package muke.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import muke.Muke;

/**
 * Represents the main window of the Muke application.
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

    private Muke muke;
    private Stage stage;
    private final Image userImage = new Image(this.getClass().getResource("/images/DaUser.png").toExternalForm());
    private final Image mukeImage = new Image(this.getClass().getResource("/images/DaMuke.png").toExternalForm());
    private boolean isDarkMode = false;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays Muke's introductory greeting.
     */
    @FXML
    public void sendIntro() {
        addMukeMessage(muke.getGreeting());
    }

    /**
     * Sets the Muke instance.
     *
     * @param d The Muke instance to be used
     */
    public void setMuke(Muke d) {
        muke = d;
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
            String response = muke.getResponse(input);
            addMukeMessage(response);
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
     * Adds a Muke message to the dialog container.
     *
     * @param message Muke's response message
     */
    private void addMukeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getMukeDialog(message, mukeImage));
    }
}