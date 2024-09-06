package control;

import him.Him;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Him him;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/not_him.jpg"));
    private Image himImage = new Image(this.getClass().getResourceAsStream("/images/him.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Him instance
     */
    public void setHim(Him him) {
        this.him = him;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Him's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = "User: " + userInput.getText();
        String himText = him.getResponse(userInput.getText());
        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(userText, userImage), DialogBox.getHimDialog(himText, himImage));
        userInput.clear();
    }
}
