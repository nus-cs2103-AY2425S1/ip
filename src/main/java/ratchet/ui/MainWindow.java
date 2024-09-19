package ratchet.ui;

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
import ratchet.Ratchet;

/**
 * Main window of GUI for ratchet
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image ratchetImage = new Image(this.getClass().getResourceAsStream("/images/Ratchet.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Ratchet ratchet;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRatchet(Ratchet ratchet) {
        this.ratchet = ratchet;
        dialogContainer.getChildren().add(DialogBox.getRatchetDialog(ratchet.getUi().greet(), ratchetImage,
                false));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ratchet.getResponse(input);
        boolean isError = response.startsWith("Invalid");
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getRatchetDialog(response, ratchetImage, isError));
        userInput.clear();
        if (input.equals("bye")) {
            userInput.setVisible(false);
            sendButton.setVisible(false);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
