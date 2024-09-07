package pochat.display;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import pochat.bot.ChatData;
import pochat.bot.PoChat;
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

    private PoChat poChat;
    private final ChatData chatData = new ChatData("src//main//chat_data.txt");

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private final Image poChatImage = new Image(this.getClass().getResourceAsStream("/images/pochat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the PoChat instance */
    public void setPoChat(PoChat poChat) {
        this.poChat = poChat;
        this.poChat.load(chatData);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PoChat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.poChat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, poChatImage)
        );
        userInput.clear();
        this.poChat.save(chatData);
    }
}

