package snipe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import snipe.core.Snipe;
import snipe.exception.SnipeException;

import java.io.IOException;

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

    private Snipe snipe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image snipeImage = new Image(this.getClass().getResourceAsStream("/images/DaSnipe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Snipe instance */
    public void setSnipe(Snipe s) {
        this.snipe = s;
        String welcomeMessage = snipe.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getSnipeDialog(welcomeMessage, snipeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Snipe's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, SnipeException {
        String input = userInput.getText();
        String response = snipe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnipeDialog(response, snipeImage)
        );
        userInput.clear();
    }
}

