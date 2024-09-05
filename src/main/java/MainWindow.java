import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.Ui;

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

    private Downy downy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image downyImage = new Image(this.getClass().getResourceAsStream("/images/Downy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Downy instance */
    public void setDowny(Downy d) {
        downy = d;
        String welcomeMessage = Ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getDownyDialog(welcomeMessage, downyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Downy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = downy.executeCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDownyDialog(response, downyImage)
        );

        // Check if the command is the exit command (e.g., "bye")
        if (input.equalsIgnoreCase("bye")) {
            // Show the goodbye message and then close the app after a delay
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Main.exitApp());
            delay.play();
        }
        userInput.clear();
    }
}
