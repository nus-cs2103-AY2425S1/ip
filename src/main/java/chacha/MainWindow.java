package chacha;

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

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String GREETING = "Hello! I'm ChaCha the ChatBot. :) \n"
            + "What can I do for you? \n";
    // Used AI for help with formatting the application, asking for common functions to use and how to use them.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private ChaCha chacha;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image chachaImage = new Image(this.getClass().getResourceAsStream("/images/ChaChaImage.png"));

    /**
     * Initializes the main window by setting up the scroll pane and adding the initial greeting dialog.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(DialogBox.getChaChaDialog(GREETING, chachaImage));
    }

    /** Injects the Duke instance */
    public void setChaCha(ChaCha d) {
        this.chacha = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChaCha's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (this.chacha.getIsEnd()) {
            this.closeApplication();
        }
        String input = userInput.getText();
        String response = chacha.getResponse(input);
        assert !response.isEmpty() : "response should not be empty";

        assert userImage != null : "user image should not be null";
        assert chachaImage != null : "chacha image should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChaChaDialog(response, chachaImage)
        );
        userInput.clear();

        if (this.chacha.getIsEnd()) {
            this.closeApplication();
        }
    }

    /**
     * Closes the application after receiving certain input.
     */
    private void closeApplication() {
        // Used AI to learn how to close the application with response to certain input.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(action -> Platform.exit());
        delay.play();
    }
}
