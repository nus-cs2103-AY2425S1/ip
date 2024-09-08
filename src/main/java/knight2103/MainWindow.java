package knight2103;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
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

    private Knight2103 knight2103;

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/Koobit_Jaguar.png"));
    private Image knight2103Image = new Image(
            this.getClass().getResourceAsStream("/images/Koobit_Water_Droplet.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Knight2103 instance.
     *
     * @param chatbot The bot instance to be part of the programme.
     */
    public void setKnight2103(Knight2103 chatbot) {
        knight2103 = chatbot;
    }

    /**
     * Allows bot to start talking (show its response) when GUI is immediately opened.
     */
    public void startBotConvo() {
        dialogContainer.getChildren().addAll(
                DialogBox.getKnight2103Dialog(knight2103.triggerWelcome(), knight2103Image));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot's reply and then
     * appends them to the dialog container. Clears the user input after processing. If the bot's response
     * contains the response that is triggered by the Bye Command, the input textfield will be locked (user
     * cannot input) and a placeholder text will be shown in the input to indicate that the bot has exited.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = knight2103.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getKnight2103Dialog(response, knight2103Image));
        userInput.clear();

        if (response.contains("Bye. Hope to see you again soon!")) {
            // @@author cth06-Github-reused
            // Reused from https://coderanch.com/t/713720 with minor modifications
            userInput.setEditable(false);
            // @@author
            userInput.setPromptText("Bot has exited. To start chatting, please close and restart");
        }
    }
}
