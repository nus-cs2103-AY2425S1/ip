package orion.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import orion.chatbot.Orion;

/**
 * Controller for the main GUI.
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

    private Orion orion;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/blank-user.png"));
    private Image orionImage = new Image(this.getClass().getResourceAsStream("/images/orion-62979_1280.jpg"));

    /**
     * Initialises the GUI component for the Orion application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hello from Orion! We've fetched your task list from the cosmos!";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, orionImage)
        );
    }

    /** Injects the Orion instance */
    public void setOrion(Orion o) {
        orion = o;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Orion's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = orion.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, orionImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}
