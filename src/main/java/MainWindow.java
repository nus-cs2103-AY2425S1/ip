import astor.Astor;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Astor astor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-modified.png"));
    private Image astorImage = new Image(this.getClass().getResourceAsStream("/images/astor2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setAstor(Astor astor) {
        assert astor != null : "astor must not be null";

        this.astor = astor;
        this.dialogContainer.getChildren().addAll(DialogBox.getAstorDialog(astor.welcomeMessage(), astorImage,
                "not_a_command"));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = astor.getResponse(input);
        String commandType = astor.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAstorDialog(response, astorImage, commandType)
        );
        userInput.clear();
        if (response.startsWith("Bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
            delay.setOnFinished(e -> Platform.exit()); // Close the app after the delay
            delay.play();
        }
    }
}
