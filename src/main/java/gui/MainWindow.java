package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rizzler.Rizzler;

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

    private Rizzler rizzler;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image rizzlerImage = new Image(this.getClass().getResourceAsStream("/images/Rizzler.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rizzler instance */
    public void setRizzler(Rizzler d) {
        assert d != null : "rizzler must not be null";
        assert userImage != null : "userImage must not be null";
        assert rizzlerImage != null : "rizzlerImage must not be null";
        rizzler = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rizzler's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "userInput must not be null";
        String response = rizzler.getResponse(input);
        assert response != null : "response must not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRizzlerDialog(response, rizzlerImage)
        );
        userInput.clear();
        if (rizzler.shouldEnd()) {
            Platform.exit();
        }
    }
}
