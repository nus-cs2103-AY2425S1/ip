package thanos.ui.components;

import java.util.Objects;

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
import thanos.Thanos;

/**
 * Represents the main window of the application, handling user interactions and displaying dialog messages.
 * <p>
 * The {@code MainWindow} class extends {@code AnchorPane} and manages the user interface components,
 * including the text input field, send button, and the container for dialog messages.
 * </p>
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

    private Thanos thanos;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/TonyStark.jpg")));
    private final Image thanosImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Thanos.jpg")));
    private final String greeting = "Greetings, mortal!\nI'm Thanos, your ever-watchful task management assistant.\n"
            + "Ready to snap away your worries?\nLet's get started!";

    /**
     * Initializes the main window to ensure that the scroll pane scrolls automatically to show the
     * most recent dialog messages.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getThanosDialog(greeting, thanosImage)
        );
    }

    /**
     * Sets the {@code Thanos} instance for processing user commands.
     *
     * @param thanos the {@code Thanos} instance to be set.
     */
    public void setThanos(Thanos thanos) {
        this.thanos = thanos;
    }

    /**
     * Handles user input from the text field and updates the dialog container with the user's input and the response.
     * <p>
     * This method processes the user's text input, generates a response from the {@code Thanos} instance,
     * and updates the dialog container with both the user's message and the response. If the response indicates
     * a farewell, the application will exit after a short delay.
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = thanos.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getThanosDialog(response, thanosImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!\n")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
