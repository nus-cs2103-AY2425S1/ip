package sentinel.gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sentinel.Sentinel;
import sentinel.exception.SentinelException;

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

    private final Image sentinelImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Sentinel.jpeg")));
    private final Image sadSentinelImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/sadSentinel.jpeg")));

    /**
     * Initializes the UI components and sets up the initial state of the application.
     * This method binds the vertical value property of the scroll pane to the height property
     * of the dialog container to ensure that the scroll pane automatically scrolls as more content
     * is added to the dialog container.
     * <p>
     * Additionally, it adds a welcome message dialog box to the dialog container. The message
     * is retrieved from the `SentinelString.stringWelcome()` method, and the Sentinel image is used for
     * displaying the message.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                SentinelDialogBox.getDialog("Hi, I'm Sentinel! How can I help you today?", sentinelImage)
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
     * Creates two dialog boxes, one echoing user input and the other containing Sentinel's
     * reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response;
            HBox sentinelReply;
            try {
                response = this.sentinel.getResponse(input);
                sentinelReply = SentinelDialogBox.getDialog(response, sentinelImage);
            } catch (SentinelException e) {
                sentinelReply = SentinelErrorBox.getDialog(e.getMessage(), sadSentinelImage);
            }
            dialogContainer.getChildren().addAll(
                    UserDialogBox.getDialog(input),
                    sentinelReply
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
