package sora.GUI;

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
import sora.Sora;

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

    private Sora sora;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kirby.jpg"));
    private Image soraImage = new Image(this.getClass().getResourceAsStream("/images/Deer.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sora instance */
    public void setSora(Sora d) {
        sora = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sora.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, soraImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }

    protected void greetUser() {
        String response = sora.getResponse("welcome");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, soraImage)
        );
    }
}
