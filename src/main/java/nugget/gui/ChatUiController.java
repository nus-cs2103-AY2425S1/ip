package nugget.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import nugget.Nugget;

/**
 * Controller class for the Chat UI in a Nugget application.
 * This class handles user input, displays messages in the chat, and integrates with the Nugget backend.
 */
public class ChatUiController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox messageArea;
    @FXML
    private TextField inputField;
    @FXML
    private Button submitButton;

    private Nugget nugget;
    private Image userImage;
    private Image botImage;

    /**
     * Initializes the ChatUiController.
     * Loads the user and bot images and sets up automatic scrolling for the chat area.
     */
    @FXML
    public void initialize() {
        userImage = new Image(getClass().getResourceAsStream("/images/nugget.jpeg"));
        botImage = new Image(getClass().getResourceAsStream("/images/bot.jpeg"));
        messageArea.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets the Nugget instance used by the controller.
     *
     * @param nugget The Nugget instance to handle user input.
     */
    public void setNugget(Nugget nugget) {
        this.nugget = nugget;
    }

    @FXML
    private void handleInput() {
        String input = inputField.getText();
        if (!input.trim().isEmpty()) {
            addMessage(input, true);
            nugget.handleInput(input);
            inputField.clear();
            inputField.requestFocus();
        }
    }

    public void addMessage(String message, boolean isUser) {
        messageArea.getChildren().add(MessageHandler.createMessageBox(message, isUser, userImage, botImage));
    }

    // This method is called by the Ui class to update output in the GUI
    public void updateOutput(String message) {
        addMessage(message, false);
    }
}
