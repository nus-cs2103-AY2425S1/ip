package chatgpt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChatGpt chatGpt;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image chatImage = new Image(this.getClass().getResourceAsStream("/images/ChatGPT.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getChatDialog(ChatGpt.getWelcome(), chatImage)
        );

    }

    /** Injects the ChatGPT instance */
    public void setChat(ChatGpt c) {
        chatGpt = c;
        if (chatGpt.hasErrorLoading()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getChatDialog(ChatGpt.getLoadingError(), chatImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatGpt.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatDialog(response, chatImage)
        );
        if (input.equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit();
        }
        userInput.clear();
    }
}
