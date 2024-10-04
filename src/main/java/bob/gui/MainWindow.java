package bob.gui;

import bob.Bob;
import javafx.application.Platform;
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

    private Bob bob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_avatar.jpg"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/Bob_avatar.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
    }

    /** Injects the Bob instance */
    public void setBob(Bob b) {
        bob = b;
        String greeting = bob.getLastMessage();
        if (!greeting.isBlank()) {
            dialogContainer.getChildren().add(DialogBox.getBobDialog(greeting, bobImage, ""));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        String commandType = bob.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage, commandType)
        );
        userInput.clear();
        if (bob.isExit()) {
            Platform.exit();
        }
    }
}