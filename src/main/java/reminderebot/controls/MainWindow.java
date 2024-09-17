package reminderebot.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import reminderebot.Reminderebot;

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

    private Reminderebot reminderebot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image reminderebotImage = new Image(this.getClass().getResourceAsStream("/images/Remindere.png"));

    /**
     * Initialise Reminderebot GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Reminderebot instance with greeting message
     * */
    public void setReminderebot(Reminderebot reminderebot) {
        assert reminderebot != null;
        this.reminderebot = reminderebot;
        String greeting = reminderebot.start();
        dialogContainer.getChildren().add(
                DialogBox.getReminderebotDialog(greeting, reminderebotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null;
        String response = reminderebot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getReminderebotDialog(response, reminderebotImage)
        );
        userInput.clear();
    }
}
