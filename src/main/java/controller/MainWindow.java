package controller;

import blitz.Blitz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Blitz blitz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image blitzImage = new Image(this.getClass().getResourceAsStream("/images/Blitz.jpg"));

    /**
     * Initializes the MainWindow by setting up the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBlitzDialog("Hello, I am Blitz! How can I help you?", blitzImage)
        );
        userInput.setPromptText("Type here");
    }

    /** Injects the Blitz instance */
    public void setBlitz(Blitz b) {
        blitz = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blitz.getResponse(input);

        if (response.equalsIgnoreCase("bye")) {
            System.exit(0);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBlitzDialog(response, blitzImage)
        );
        userInput.clear();
    }
}
