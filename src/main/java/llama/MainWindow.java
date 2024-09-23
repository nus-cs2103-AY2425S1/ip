package llama;

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

    private Llama llama;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image llamaImage = new Image(this.getClass().getResourceAsStream("/images/Llama.png"));

    /**
     * Starts up GUI MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setLlama(Llama l) {
        llama = l;
        // Greet User once Llama is set up
        String response = llama.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, llamaImage)
        );
    }

    /**
     * Creates two dialog boxes, one for user to reflect input, another for llama to reflect response
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = llama.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, llamaImage)
        );
        userInput.clear();

        if (input.toLowerCase().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
        }
    }
}
