package yihuibot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 *
 * @author Toh Yi Hui A0259080A
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

    private YihuiBot yihuiBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chad-cat.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/smiling-cat.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the YihuiBot instance.
     *
     * @param yihuiBot the bot instance.
     */
    public void setBot(YihuiBot yihuiBot) {
        this.yihuiBot = yihuiBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot's
     * reply and then appends them to the dialog container. Clears the user input after
     * processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yihuiBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
