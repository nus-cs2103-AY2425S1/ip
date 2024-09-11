package rex;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rex.util.Ui;

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

    private Rex rex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rexImage = new Image(this.getClass().getResourceAsStream("/images/Rex.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rex instance */
    public void setRex(Rex rex) {
        this.rex = rex;
        String greetingMessage = Ui.getGreeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getRexDialog(greetingMessage, rexImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rex's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rex.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRexDialog(response, rexImage)
        );

        // Exit application if bye command entered
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Main.exit());
            delay.play();
        }
        userInput.clear();
    }
}
