package alfred.ui;

import alfred.Alfred;
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

    private Alfred alfred;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/batman.png"));
    private Image alfredImage = new Image(this.getClass().getResourceAsStream("/images/alfred.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the alfred instance */
    public void setAlfred(Alfred alfred) {
        this.alfred = alfred;
        displayAlfredResponse(AlfredResponse.greet());

        String loadingError = alfred.getLoadingError();
        if (loadingError != null) {
            displayAlfredResponse(loadingError);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing alfred's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alfred.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlfredDialog(response, alfredImage)
        );
        userInput.clear();

        // Stop the JavaFX application after time delay if bye command given
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

    /**
     * Displays a message from Alfred without any user input.
     *
     * @param response The message that Alfred will send.
     */
    public void displayAlfredResponse(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getAlfredDialog(response, alfredImage)
        );
    }
}
