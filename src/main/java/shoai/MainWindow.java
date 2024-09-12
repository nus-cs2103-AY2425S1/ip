package shoai;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ShoAI chatbot;
    private boolean welcomed;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/shoai.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }


    public void setChatbot(ShoAI c) {
        chatbot = c;
    }

    @FXML
    private void handleUserInput() {
        if (!welcomed) {
            showWelcomeMessage();
            welcomed = true;
        }
        String input = userInput.getText();
        String response = chatbot.getResponse(input); // uses parser to get response
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatbotDialog(response, chatbotImage)
        );
        userInput.clear();
    }

    private void showWelcomeMessage() {
        if (!welcomed) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getChatbotDialog("What's up! I'm ShoAI, what do you need today?",
                            chatbotImage)
            );
            welcomed = true;
        }
    }
}
