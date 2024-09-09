package taskon;

import static taskon.common.Messages.MESSAGE_GREETING;

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

    private Taskon taskon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Patrick.png"));
    private Image taskonImage = new Image(this.getClass().getResourceAsStream("/images/Spongebob.png"));

    /**
     * Initializes the MainWindow by setting up the user interface components.
     * <p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getTaskonDialog(MESSAGE_GREETING, taskonImage));
    }

    /** Injects the Taskon instance */
    public void setTaskon(Taskon t) {
        taskon = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TaskOn's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskonDialog(response, taskonImage)
        );
        userInput.clear();
    }
}
