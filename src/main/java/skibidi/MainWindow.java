package skibidi;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Skibidi skibidi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image skibidiImage = new Image(this.getClass().getResourceAsStream("/images/DaSkibidi.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Skibidi instance */
    public void setSkibidi(Skibidi s) {
        skibidi = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Skibidi's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = skibidi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkibidiDialog(response, skibidiImage)
        );

        if (input.trim().equals("bye")) {
            PauseTransition delay = new PauseTransition(javafx.util.Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }

        userInput.clear();
    }
}
