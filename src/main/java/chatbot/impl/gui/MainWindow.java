package chatbot.impl.gui;

import chatbot.interfaces.ChatBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

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

    private ChatBot bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nerd.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/atlas.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        String introduction = "Hello! I'm Atlas.\n" +
                "I am here to share your burdens of remembering tasks\n" +
                "What can I do for you today?";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(introduction, dukeImage)
        );
    }

    /** Injects the Duke instance */
    public void setBot(ChatBot b) {
        bot = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit(); 
        }
    }
}
