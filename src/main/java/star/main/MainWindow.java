package star.main;

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
    private static final String HI_MSG = "hi! I'm Star :-) what's up?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Star star;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/me.jpg"));
    private Image starImage = new Image(this.getClass().getResourceAsStream("/images/Star.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Star instance */
    public void setStar(Star s) {
        star = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Star's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println(input);
        String response = star.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();

        // Create a pause (delay)
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5)); // 0.5-second delay
        delay.setOnFinished(event -> {
            // Add Star's response after the delay
            dialogContainer.getChildren().add(
                    DialogBox.getStarDialog(response, starImage)
            );
        });

        // Start the delay
        delay.play();
    }

    /**
     * Shows the welcome message from Star's side
     */
    public void showStarStartMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getStarDialog(HI_MSG, starImage)
        );
    }
}

