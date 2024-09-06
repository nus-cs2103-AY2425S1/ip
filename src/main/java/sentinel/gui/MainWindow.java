package sentinel.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sentinel.Sentinel;

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
    private Sentinel sentinel;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the UI components and sets up the initial state of the application.
     * This method binds the vertical value property of the scroll pane to the height property
     * of the dialog container to ensure that the scroll pane automatically scrolls as more content
     * is added to the dialog container.
     * <p>
     * Additionally, it adds a welcome message dialog box to the dialog container. The message
     * is retrieved from the `SentinelString.stringWelcome()` method, and the Duke image is used for
     * displaying the message.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hi, I'm Sentinel! How can I help you today?", dukeImage)
        );
    }

    /** Injects the Sentinel instance */
    public void setSentinel(Sentinel sentinel) {
        this.sentinel = sentinel;
    }

    /** Injects the stage */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = this.sentinel.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Create a PauseTransition of 2 seconds
            pause.setOnFinished(event -> stage.close()); // Close the stage when the pause is finished
            pause.play(); // Start the pause transition
        }
        userInput.clear();
    }
}
