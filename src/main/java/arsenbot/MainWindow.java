package arsenbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private ArsenBot arsenBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image ArsenImage = new Image(this.getClass().getResourceAsStream("/images/ArsenBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Set styles programmatically
        userInput.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14; -fx-text-fill: #333333; -fx-background-color: #f0f0f0;");
        sendButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        dialogContainer.setStyle("-fx-background-color: #ffffff;");
    }

    /** Injects the Bot instance */
    public void setBot(ArsenBot bot) {
        arsenBot = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ArsenBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = arsenBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArsenDialog(response, ArsenImage)
        );
        userInput.clear();
    }
}
