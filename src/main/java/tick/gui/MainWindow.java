package tick.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tick.Tick;

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

    private Tick tick;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image tickImage = new Image(this.getClass().getResourceAsStream("/images/Tick.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tick instance */
    public void setTick(Tick t) {
        tick = t;
    }

    public void setGreetingMessage() {
        String greetingMessage = "System starting up...\n"
                + "Brrt brrt! I'm Tick!\n"
                + "What can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getTickDialog(greetingMessage, tickImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tick.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTickDialog(response, tickImage)
        );
        userInput.clear();
    }
}
