package chappy.util;

import java.io.IOException;
import chappy.Chappy;
import chappy.exception.CreateTaskException;
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

    private Chappy chappy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image chappyImage = new Image(this.getClass().getResourceAsStream("/images/chappy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setChappy(Chappy c) {
        this.chappy = c;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws CreateTaskException, IOException {
        String userText = userInput.getText();
        String chappyText = chappy.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getChappyDialog(chappyText, chappyImage)
        );
        userInput.clear();
    }
}
