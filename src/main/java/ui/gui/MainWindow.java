package ui.gui;

import bob.Bob;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    private Bob bob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBob(Bob b) {
        bob = b;
        this.showWelcomeMessage();
        this.showReminders();
    }

    /**
    Shows welcome message to user upon opening app.
     */
    private void showWelcomeMessage() {
        DialogBox dialogBox = DialogBox.getDukeDialog(bob.ui.showWelcomeMessage(), dukeImage);
        dialogContainer.getChildren().addAll(dialogBox);
    }

    private void showReminders() {
        DialogBox dialogBox = DialogBox.getDukeDialog(bob.ui.showRemindMessage(), dukeImage);
        dialogContainer.getChildren().addAll(dialogBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // closes the application if user typed "bye"
        if (input.equalsIgnoreCase("bye")) {
            //@@author James_D -reused
            // source:
            // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            // reused the method to close the javafx window after a delay
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            //@@author
        }
    }
}

