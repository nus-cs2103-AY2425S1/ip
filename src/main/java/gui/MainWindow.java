package gui;

import friendlybot.FriendlyBot;
import friendlybot.Ui;
import friendlybot.command.BadCommand;
import friendlybot.command.Command;
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

    private FriendlyBot friendlyBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-icon.png"));
    private Image friendlyBotImage = new Image(this.getClass().getResourceAsStream("/images/friendlybot-icon.png"));

    /** Initializes the MainWindow instance */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getFriendlyBotDialog("Hello! I am Friendly Bot! How may I assist you today?\n"
                        + "Use 'help' to get started! :-)",
                        friendlyBotImage));
    }

    /** Injects the FriendlyBot instance */
    public void setFriendlyBot(FriendlyBot fb) {
        friendlyBot = fb;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * FriendlyBot's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command cmd = friendlyBot.getCommand(input);
        String response = friendlyBot.getResponse(cmd);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox friendlyBotDialog = DialogBox.getFriendlyBotDialog(response, friendlyBotImage);
        if (cmd instanceof BadCommand) {
            friendlyBotDialog.setErrorMessage();
        }

        dialogContainer.getChildren().addAll(userDialog, friendlyBotDialog);
        userInput.clear();
        if (response.equals(Ui.getExitMessage())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
