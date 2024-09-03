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

/**
 * Controller for the main GUI.
 * <p>
 * The {@code MainWindow} class handles the primary user interface of the Talkie application. It manages the dialog
 * between the user and Talkie, displays the responses, and handles application exit based on user commands.
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

    private Talkie talkie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/travis.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/baby-yoda.png"));

    /**
     * Initializes the main window components.
     * <p>
     * This method binds the scroll pane's vertical value to the dialog container's height to ensure that new messages
     * are always visible. It also sets the font for the input and send button and displays a greeting message in the
     * dialog container.
     * </p>
     */
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

    /**
     * Injects the Talkie instance into this controller.
     * <p>
     * This method is used to set the Talkie instance that the {@code MainWindow} interacts with.
     * </p>
     *
     * @param t The Talkie instance to be used by this controller.
     */
    public void setTalkie(Talkie t) {
        talkie = t;
    }

    /**
     * Handles user input and generates a response from Talkie.
     * <p>
     * This method reads the user's input, processes it to generate a response, and displays both the input and response
     * in dialog boxes. If the user inputs "bye", the application will close after a short delay.
     * </p>
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

    /**
     * Closes the application after a short delay.
     * <p>
     * This method creates a 1-second pause before exiting the application. It is used to give the user a moment to see
     * the final response from Talkie before the application closes.
     * </p>
     */
    private void closeAfterDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
