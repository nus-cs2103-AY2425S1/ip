package features.gui;

import config.Config;
import features.Susan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Objects;

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

    private Susan susan;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
    private final Image susanImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/susan.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox susanDialog = DialogBox.getSusanDialog(Config.intro, susanImage);
        susanDialog.getStyleClass().add("bot-dialog");
        dialogContainer.getChildren().addAll(susanDialog);
    }

    /** Injects the Susan instance */
    public void setBot(Susan d) {
        susan = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = susan.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox susanDialog = DialogBox.getSusanDialog(response, susanImage);
        userDialog.getStyleClass().add("user-dialog");
        susanDialog.getStyleClass().add("bot-dialog");
        DialogBox.getUserDialog(input, userImage).getStyleClass().add("user-dialog");
        dialogContainer.getChildren().addAll(userDialog, susanDialog);
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            // Set the action to perform after the delay (exit the program)
            delay.setOnFinished(event -> System.exit(0));
            // Start the transition, allowing the "bye" message to be shown
            delay.play();
        }
    }
}
