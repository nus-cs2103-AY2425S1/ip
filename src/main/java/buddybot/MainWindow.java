package buddybot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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

    private BuddyBot buddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image buddyImage = new Image(this.getClass().getResourceAsStream("/images/DaBuddyBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = "Welcome! I'm BuddyBot.\nHow may I help you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, buddyImage)
        );
    }

    /** Injects the Duke instance */
    public void setBuddy(BuddyBot b) {
        buddy = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = buddy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, buddyImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            TimerTask myTask = new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            };
            Timer timer = new Timer("Delay");
            timer.schedule(myTask, 1000L);
        }
    }
}
