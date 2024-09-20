package shrimp.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import shrimp.Shrimp;

public class MainWindow {
    private Shrimp shrimp;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/dango.jpeg"));
    private final Image shrimpImage = new Image(this.getClass().getResourceAsStream("/images/shrimp.jpeg"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Shrimp instance
     */
    public void setShrimp(Shrimp shrimp) {
        this.shrimp = shrimp;
        String error = shrimp.runChatBot();
        if (!error.isEmpty()) {
            showMessage(error);
        }
        showMessage(shrimp.showWelcome());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = shrimp.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                response.contains("ERR") ? DialogBox.getErrorDialog(response, shrimpImage) : DialogBox.getShrimpDialog(response, shrimpImage)
        );
        if (response.contains("Bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }

    private void showMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getShrimpDialog(message, shrimpImage));
    }
}
