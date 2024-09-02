package delta.gui;

import delta.Delta;
import javafx.animation.PauseTransition;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Delta delta;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image deltaImage = new Image(this.getClass().getResourceAsStream("/images/Delta.png"));

    /**
     * Initializes the main window with size setting and welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDeltaDialog("""
                Hello! I'm Delta, your favourite Task Management Chatbot!
                What can I do for you?""", deltaImage));
    }

    /** Injects the Delta instance */
    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Delta's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        String input = userInput.getText();
        String response = delta.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        pause.setOnFinished(event -> dialogContainer.getChildren().addAll(
                DialogBox.getDeltaDialog(response, deltaImage)));
        pause.play();
        userInput.clear();
        if (response.equals("Bye. Hope to see you again soon!")) {
            pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }
}
