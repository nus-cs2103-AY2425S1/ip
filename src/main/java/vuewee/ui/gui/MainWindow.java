package vuewee.ui.gui;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gopher.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/vue.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void sendBotMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(message, botImage));
    }

    /**
     * Creates a dialog box containing user input, and appends it to the dialog
     * container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        this.dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage));
        userInput.clear();
        VueweeGui.processUserInput(userText);
    }
}
