package socchat.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import socchat.Socchat;

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

    private Socchat socchat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image soccatImage = new Image(this.getClass().getResourceAsStream("/images/soccat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Socchat instance */
    public void setSocchat(Socchat s) {
        socchat = s;
        dialogContainer.getChildren().addAll(
                DialogBox.getSocchatDialog(Socchat.greet(), soccatImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Socchat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = socchat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSocchatDialog(response, soccatImage)
        );
        userInput.clear();
    }
}

