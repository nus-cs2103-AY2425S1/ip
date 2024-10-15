package lunabot.gui;

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
import lunabot.LunaBot;

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

    private LunaBot luna;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/botImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance.
     * Prints a welcome message for the user when the bot is started.
     * */
    public void setLunaBot(LunaBot lb) {
        luna = lb;
        String welcomeMessage = luna.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(welcomeMessage, botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );

        // If input is "bye", close the application
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        }
        userInput.clear();
    }
}
