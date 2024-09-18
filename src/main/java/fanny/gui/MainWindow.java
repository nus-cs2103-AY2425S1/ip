package fanny.gui;

import fanny.Fanny;
import fanny.ui.Ui;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fanny fanny;

    /** Avatar designed by Freepik */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Profile-Avatar.png"));
    /** Avatar designed by Freepik */
    private Image fannyImage = new Image(this.getClass().getResourceAsStream("/images/fanny-avatar.png"));

    /**
     * Initializes the main window of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showStartupMessage();
    }

    /**
     * Injects the Fanny instance.
     *
     * @param f The instance of Fanny.
     */
    public void setFanny(Fanny f) {
        fanny = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fanny.generateResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFannyDialog(response, fannyImage)
        );
        userInput.clear();
        handleExit(input);
    }

    /**
     * Handles the exit of application if the input command is "bye"
     *
     * @param input The command to be executed.
     */
    private void handleExit(String input) {
        if (input.trim().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }

    /**
     * Displays the startup message from the Ui class in the dialog container.
     */
    private void showStartupMessage() {
        String welcomeMessage = new Ui().printHello();
        String reminderMessage = Fanny.getReminders();
        String startUpMessage = welcomeMessage + "\n" + reminderMessage;

        dialogContainer.getChildren().add(
                DialogBox.getFannyDialog(startUpMessage, fannyImage)
        );
    }

}
