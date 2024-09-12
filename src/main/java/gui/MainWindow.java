package gui;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rizzler.jpg"));
    private Image rizzlerImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rizzler instance */
    public void setRizzler(Rizzler d) {
        rizzler = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rizzler's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rizzler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRizzlerDialog(response, rizzlerImage)
        );
        userInput.clear();
    }
}
