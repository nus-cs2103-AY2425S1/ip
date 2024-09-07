package ahmad.fx;


import static ahmad.Ahmad.processUserMessage;
import static javafx.application.Platform.exit;

import java.io.IOException;

import ahmad.Ahmad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller class for JavaFX
 */
public class Controller {

    @FXML
    private TextField userInput;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Runs the required processes during first initialization.
     */
    @FXML
    public void initialize() {
        userInput.setOnAction((event) -> {
            handleUserSend();
        });

        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    @FXML
    private void handleUserSend() {
        String inputText = userInput.getText().trim();
        pushUserMessage(inputText);
        if (processUserMessage(inputText)) {
            exit();
        }
    }

    private void pushUserMessage(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(Ahmad.class.getResource("/fx/UserChat.fxml"));

        try {
            chatBox.getChildren().add(fxmlLoader.load());
            UserChatController userChatController = fxmlLoader.getController();
            userChatController.setText(message);

            userInput.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pushes a message to bot.
     *
     * @param message Message to be pushed/printed.
     */
    public void pushBotMessage(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(Ahmad.class.getResource("/fx/BotChat.fxml"));

        try {
            chatBox.getChildren().add(fxmlLoader.load());
            UserChatController userChatController = fxmlLoader.getController();
            userChatController.setText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
