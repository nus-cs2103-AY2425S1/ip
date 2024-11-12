package lemon.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lemon.Lemon;

/**
 * Controller for the main GUI.
 */
public class LemonFxWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lemon lemon;

    // Picture from https://custom-cursor.com/en/collection/cute-cursors/cute-lemon
    private Image lemonImage = new Image(this.getClass().getResourceAsStream("/images/Lemonww.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User1.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Lemon instance */
    public void setLemon(Lemon l) {
        lemon = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lemon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input + " ", userImage)
        );
        userInput.clear();

        lemon.respond(input);
    }

    /**
     * Add lemon {@link DialogBox} with the response from lemon
     * @param response returned by lemon to be displayed
     */
    public void addLemonDialogBox(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getLemonDialog(response, lemonImage)
        );
    }

    /**
     * Disable the input field and button
     */
    public void disable() {
        userInput.setEditable(false);
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
