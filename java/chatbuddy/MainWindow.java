package chatbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the ChatBuddy application.
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

    private ChatBuddy chatBuddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image chatbuddyImage = new Image(this.getClass().getResourceAsStream("/images/chatbuddyImage.png"));

    @FXML
    public void initialize() {
        // Scroll pane setup
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the ChatBuddy instance into the MainWindow controller.
     *
     * @param chatBuddy The ChatBuddy instance to be injected.
     */
    public void setChatBuddy(ChatBuddy chatBuddy) {
        this.chatBuddy = chatBuddy;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(chatBuddy.getWelcomeMessage(), chatbuddyImage)
        );
    }

    /**
     * Handles user input by creating dialog boxes for the user's input and ChatBuddy's response.
     * It also clears the input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatBuddy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, chatbuddyImage)
        );
        userInput.clear();
    }
}
