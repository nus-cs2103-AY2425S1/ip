package alexer.ui;

import alexer.Alexer;
import alexer.Prompter;
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

    /** Image for the user **/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aUser.png"));

    /** Image for the chatbot/Alexer **/
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/aAlexer.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(MessageBox.createBotDialog(Prompter.buildGreeting(), botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alexer's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = Alexer.getInstance().processInput(input);

        // ensure our images are not null
        assert userImage != null;
        assert botImage != null;

        dialogContainer.getChildren().add(MessageBox.createUserDialog(input, userImage));
        if (response != null) {
            MessageBox message = MessageBox.createBotDialog(response, botImage);
            dialogContainer.getChildren().add(message);
            message.animateText();
        }
        userInput.clear();
    }
}
