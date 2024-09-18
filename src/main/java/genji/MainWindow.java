package genji;

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

    private Genji genji;

    private Image userImage = new Image(this.getClass().
            getResourceAsStream("/images/DaUser.png"));
    private Image GenjiImage = new Image(this.getClass().
            getResourceAsStream("/images/DaGenji.png"));

    /**
     * Initializes the program
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getGenjiDialog(Ui.welcome(), GenjiImage)
        );
    }

    /**
     * Injects the Genji instance
     */
    public void setGenji(Genji g) {
        genji = g;
        assert genji != null : "Genji not set successfully";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Genji's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = genji.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGenjiDialog(response, GenjiImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delayExit = new PauseTransition();
            delayExit.setDuration(Duration.seconds(1.5));
            delayExit.setOnFinished(event -> Platform.exit());
            delayExit.play();
        }
    }
}
