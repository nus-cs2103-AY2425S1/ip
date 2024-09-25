package bangmang.gui;

import bangmang.BangMang;
import bangmang.ui.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

    private BangMang bangMang;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/ChatbotImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showGreeting();
    }

    public void setBangMang(BangMang bangMang) {
        this.bangMang = bangMang;
    }
    
    /**
     * Displays the greeting message from the chatbot.
     */
    private void showGreeting() {
        String greeting = new Ui().showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getLmhDialog(greeting, chatbotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bangMang.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLmhDialog(response, chatbotImage)
        );
        userInput.clear();
    }
}
