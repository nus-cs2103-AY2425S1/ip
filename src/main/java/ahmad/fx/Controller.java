package ahmad.fx;


import java.io.IOException;

import ahmad.Ahmad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    @FXML
    private void handleUserSend() {
        String inputText = userInput.getText().trim();
        System.out.println(inputText);
        pushUserMessage(inputText);
        pushBotMessage(inputText);
    }

    private void pushUserMessage(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(Ahmad.class.getResource("/fx/UserChat.fxml"));

        try {
            chatBox.getChildren().add(fxmlLoader.load());
            UserChatController userChatController = fxmlLoader.getController();
            userChatController.setText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void pushBotMessage(String message) {
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
