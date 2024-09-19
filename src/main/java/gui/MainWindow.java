package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utilities.TaskFairy;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import utilities.TaskFairy;

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

    private TaskFairy taskFairy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/gui/images/DaUser.png"));
    private Image taskFairyImage = new Image(this.getClass().getResourceAsStream("/gui/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bigmouth instance */
    public void setTaskFairy(TaskFairy t) {
        taskFairy = t;
        String welcomeMessage = t.greetUser();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, taskFairyImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bigmouth's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskFairy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, taskFairyImage)
        );

        if (response.equals("Nice talk, girl. Now, get back to that hustle!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
