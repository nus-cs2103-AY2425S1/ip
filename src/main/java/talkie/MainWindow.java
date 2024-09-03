package talkie;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

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

    private Talkie talkie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/travis.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/baby-yoda.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setFont(Font.font("Verdana"));
        userInput.setFont(Font.font("Verdana"));
        String greeting = "Welcome! I'm Talkie.\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getTalkieDialog(greeting, dukeImage)
        );
    }

    /** Injects the Talkie instance */
    public void setTalkie(Talkie t) {
        talkie = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().toLowerCase().trim();
        String response = talkie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTalkieDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            closeAfterDelay();
        }
    }

    private void closeAfterDelay() {
        // Create a PauseTransition for 1 second
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        // After the pause, exit the application
        pause.setOnFinished(event -> Platform.exit());

        // Start the pause
        pause.play();
    }
}

