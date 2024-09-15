package elysia.ui;

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

    private Elysia elysia;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/captain.jpg"));
    private Image elysiaImage = new Image(this.getClass().getResourceAsStream("/images/elysia.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(Elysia elysia) {
        this.elysia = elysia;
    }

    @FXML
    public void showWelcome() {
        String welcome = "Hi~! I'm Elysia! As you can see, I'm a girl as beautiful as a flower!\n" +
                "How can I help you today? I'm all ears!";
        dialogContainer.getChildren().addAll(DialogBox.getElysiaDialog(welcome, elysiaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = elysia.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getElysiaDialog(response, elysiaImage)
        );
        userInput.clear();
    }
}
