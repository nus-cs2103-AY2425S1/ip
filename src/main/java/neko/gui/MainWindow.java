package neko.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import neko.Neko;
import neko.NekoException;

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

    private Neko neko;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image nekoImage = new Image(this.getClass().getResourceAsStream("/images/Neko.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setNeko(Neko neko) {
        this.neko = neko;
        greetUser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Neko's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = neko.handleInput(input);
        } catch (NekoException | IOException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNekoDialog(response, nekoImage)
        );
        userInput.clear();
    }

    /**
     * Create one dialog box for Neko to greet to the user.
     */
    @FXML
    private void greetUser() {
        dialogContainer.getChildren().add(DialogBox.getNekoDialog(
                neko.getGreeting(), nekoImage));
    }
}
