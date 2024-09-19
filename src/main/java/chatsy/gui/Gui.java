package chatsy.gui;

import chatsy.Chatsy;
import chatsy.exceptions.ChatsyException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the graphical user interface (GUI) controller for Chatsy.
 * Manages user interactions and displays chat dialogs for both the user and Chatsy.
 */
public class Gui {

    /** The image representing the user in the chat dialog. */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));

    /** The image representing Chatsy in the chat dialog. */
    private final Image chatsyImage = new Image(this.getClass().getResourceAsStream("/images/Chatsy.jpg"));

    @FXML
    private ScrollPane chatHistoryScrollPane;

    @FXML
    private VBox chatContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    /** Instance of the Chatsy logic to handle user commands. */
    private Chatsy chatsy;

    /**
     * Initializes the GUI by binding the scroll pane's vertical scrollbar to the chat container's height property,
     * ensuring the chat history scrolls to the bottom when new messages are added.
     */
    @FXML
    public void initialize() {
        chatHistoryScrollPane.vvalueProperty().bind(chatContainer.heightProperty());
    }

    /**
     * Sets the instance of Chatsy to be used for handling commands and displays its initial greeting.
     *
     * @param chatsy The Chatsy instance to set.
     */
    public void setChatsy(Chatsy chatsy) {
        this.chatsy = chatsy;
        addChatsyDialog(chatsy.greet(), false);
    }

    /**
     * Adds a dialog box to the chat container that represents the user's message.
     *
     * @param text The message text to be displayed in the user's dialog box.
     */
    private void addUserDialog(String text) {
        chatContainer.getChildren().add(
                DialogBox.getUserDialogBox(text, userImage)
        );
    }

    /**
     * Adds a dialog box to the chat container that represents Chatsy's response.
     * Allows specifying whether the message is an error.
     *
     * @param text           The message text to be displayed in Chatsy's dialog box.
     * @param isErrorMessage A flag indicating if the message is an error message (true if error, false otherwise).
     */
    private void addChatsyDialog(String text, boolean isErrorMessage) {
        chatContainer.getChildren().add(
                DialogBox.getChatsyDialogBox(text, chatsyImage, isErrorMessage)
        );
    }

    /**
     * Handles the user's input when the send button is clicked or the Enter key is pressed.
     * Displays the user's message in the dialog box and processes the input to get a response from Chatsy.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        addUserDialog(input);

        try {
            String response = chatsy.handleCommand(input);
            addChatsyDialog(response, false);

            userInput.clear();

            if (chatsy.getParser().parse(input).shouldExit()) {
                handleExit();
            }
        } catch (ChatsyException e) {
            addChatsyDialog(e.getMessage(), true);
        }
    }

    /**
     * Handles the exit process by saving tasks and closing the window after a brief pause.
     */
    private void handleExit() {
        chatsy.exit();

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Stage stage = (Stage) chatContainer.getScene().getWindow();
            stage.close();
        });
        pause.play();
    }
}
