package dudu.gui;

import dudu.Dudu;
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

    private Dudu dudu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/standing_cat_right.jpg"));
    private Image duduImage = new Image(this.getClass().getResourceAsStream("/images/standing_cat_left.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDudu(Dudu d) {
        dudu = d;
        welcomeUser();
    }

    /**
     * Greets user by creating a dialog box
     */
    @FXML
    private void welcomeUser() {
        String message = dudu.welcomeUser();
        dialogContainer.getChildren().addAll(
                DialogBox.getDuduDialog(message, duduImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dudu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuduDialog(response, duduImage)
        );
        userInput.clear();
    }
}

