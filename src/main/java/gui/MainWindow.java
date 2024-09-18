package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import testament.Testament;

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

    private Testament testament;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Gran.png"));
    private Image testamentImage = new Image(this.getClass().getResourceAsStream("/images/Testament.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Testament instance */
    public void setTestament(Testament d) {
        testament = d;
        String welcomeMessage = testament.welcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getTestamentDialog(welcomeMessage, testamentImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Testament's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = testament.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTestamentDialog(response, testamentImage)
        );
        userInput.clear();
    }
}
