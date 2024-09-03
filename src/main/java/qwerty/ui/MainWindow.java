package qwerty.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import qwerty.Qwerty;

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

    private Qwerty qwerty;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/qwerty.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Qwerty instance */
    public void setQwerty(Qwerty qwerty) {
        this.qwerty = qwerty;
    }

    /**
     * Extracts and sends user input into Qwerty for handling.
     * Also clear the user input text field.
     */
    @FXML
    private void handleUserInput() {
        qwerty.handleUserInput(userInput.getText());
        userInput.clear();
    }

    /**
     * Creates a dialog box containing the user's message.
     *
     * @param text String of the user's message.
     */
    @FXML
    protected void showUserMessage(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(text, userImage)
        );
    }

    /**
     * Creates a dialog box containing Qwerty's message.
     *
     * @param text String of Qwerty's message.
     */
    @FXML
    protected void showQwertyMessage(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getQwertyDialog(text, dukeImage)
        );
    }
}
