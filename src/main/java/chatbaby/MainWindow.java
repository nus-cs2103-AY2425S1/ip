package chatbaby;

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
    private ChatBaby baby;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image babyImage = new Image(this.getClass().getResourceAsStream("/images/chatBaby.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the ChatBaby instance */
    public void setChatBaby(ChatBaby baby) {
        this.baby = baby;
        // Show the greeting message when the ChatBaby instance is set
        String greeting = baby.greet();
        dialogContainer.getChildren().add(DialogBox.getBabyDialog(greeting, babyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChatBaby's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";
        String response = baby.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBabyDialog(response, babyImage)
        );
        userInput.clear();
    }
}
