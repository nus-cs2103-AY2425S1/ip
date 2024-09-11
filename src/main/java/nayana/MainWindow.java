package nayana;

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

    private Nayana nayana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image nayanaImage = new Image(this.getClass().getResourceAsStream("/images/Nayana.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nayana instance */
    public void setNayana(Nayana n) {
        nayana = n;
        nayana.getUi().setVbox(dialogContainer);
        nayana.getUi().showWelcomeMessage();
    }




    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
              DialogBox.getUserDialog(input, userImage)
        );
        nayana.parseCommand(input);
        userInput.clear();
    }
}

