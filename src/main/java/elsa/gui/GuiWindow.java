package elsa.gui;

import java.util.Objects;

import elsa.ui.Elsa;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class GuiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Elsa elsa;
    private Stage stage;
    private PauseTransition pauseTransition; // Used to manage the delay after a ByeCommand and before the GUI closes

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/UserImage.png")));
    private final Image elsaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/ElsaImage.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Elsa instance */
    public void setElsa(Elsa e) {
        elsa = e;
    }

    /** Injects the Stage instance to allow for closing the window */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles user input from the TextField. Generates a response from Elsa, and updates the UI.
     * If the input contains "bye", it schedules the application to exit after 5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = elsa.getResponse(input);

        // If a previous pause transition is active, cancel the 5-second count-down timer and run as per normal.
        if (pauseTransition != null && pauseTransition.getStatus() == PauseTransition.Status.RUNNING) {
            pauseTransition.stop();
        }

        // Display Elsa's response
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getElsaDialog(response, elsaImage)
        );
        userInput.clear();

        // If the input contains "bye", close the GUI window and exit the application after waiting for 5 seconds.
        // The ByeCommand will be executed by elsa.getResponse(input) which will save the taskList to the data file.
        if (input.trim().toLowerCase().contains("bye")) {
            // Create a PauseTransition that waits 5 seconds before exiting
            pauseTransition = new PauseTransition(Duration.seconds(5));
            pauseTransition.setOnFinished(event -> {
                Platform.exit();
                stage.close();
            });
            pauseTransition.play(); // Start the 5-second timer
        }
    }
}
