package bestie;

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

    private Bestie bestie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Me.png"));
    private Image bestieImage = new Image(this.getClass().getResourceAsStream("/images/Bestie.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects instance of Bestie, start by printing welcome message */
    public void setBestie(Bestie d) {
        bestie = d;
        String welcome = bestie.welcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getBestieDialog(welcome, bestieImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing bestie's reply and then appends them to
     * the dialog container. Clears user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bestie.getResponse(input);
        assert response != null : "Failed to load response from chatbot.";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBestieDialog(response, bestieImage)
        );
        userInput.clear();
    }
}
