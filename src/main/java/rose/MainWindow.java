package rose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window. Provides the layout for the other controls.
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

    private Rose rose;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image roseImage = new Image(this.getClass().getResourceAsStream("/images/RoseImage.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String openingMessage = "Hi gorgeous! I'm Rose~~\nHow can I help you today?";
        dialogContainer.getChildren().add(DialogBox.getRoseDialog(openingMessage, roseImage));
    }

    /** Injects the Rose instance */
    public void setRose(Rose r) {
        rose = r;
    }

    /**
     * Creates two dialog boxes: One for user, and one for Rose.
     * Clears the user input after processing.
     * If user input is "bye", text field and send button will be disabled.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rose.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRoseDialog(response, roseImage)
        );
        userInput.clear();

        // Check if the user has entered "bye"
        if (input.equalsIgnoreCase("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}
