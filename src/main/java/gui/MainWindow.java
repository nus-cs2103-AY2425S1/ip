package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import michael.Michael;
import michael.MichaelException;
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

    private Michael michael;

    // Images from icons-for-free.com
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image michaelImage = new Image(this.getClass().getResourceAsStream("/images/michael.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Michael instance */
    public void setMichael(Michael m) {
        michael = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = michael.getParser().parse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMichaelDialog(response, michaelImage)
            );
            userInput.clear();
        } catch (MichaelException e) {
            dialogContainer.getChildren().add(DialogBox.getMichaelDialog(e.getMessage(), michaelImage));
        }
    }
}
