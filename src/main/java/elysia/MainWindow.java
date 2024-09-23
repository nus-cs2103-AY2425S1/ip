package elysia;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    private Elysia elysia;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image elysiaImage = new Image(this.getClass().getResourceAsStream("/images/Elysia.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Elysia instance
     */
    public void setElysia(Elysia e) {
        elysia = e;
        dialogContainer.getChildren().addAll(
                DialogBox.getElysiaDialog(this.elysia.getFirstMessage(), elysiaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits the application if user keys in "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = elysia.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getElysiaDialog(response, elysiaImage)
        );
        userInput.clear();

        if (elysia.IsExit()) {
            // Schedule the application to exit after 1.5 seconds
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1250),
                    event -> Platform.exit()
            ));
            timeline.play();
        }
    }
}
