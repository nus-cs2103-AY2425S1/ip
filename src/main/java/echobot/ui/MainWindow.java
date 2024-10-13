package echobot.ui;

import echobot.EchoBot;
import echobot.command.Command;
import echobot.command.CommandResponse;
import echobot.exception.EchoBotException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private EchoBot echoBot;


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(DialogBox.getEchobotDialog(new CommandResponse(Command.CommandType.GREETING, "Hello! I'm EchoChat\nWhat can I do for you?")));
    }

    /**
     * Injects the Duke instance
     */
    public void setEchobot(EchoBot echobot) {
        this.echoBot = echobot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        try {
            CommandResponse response = echoBot.generateResponseFromUserCommand(input);
            if (response.getCommandType().equals(Command.CommandType.EXIT)) {
                Platform.exit();
            }
            dialogContainer.getChildren().add(DialogBox.getEchobotDialog(response));
        } catch (EchoBotException e) {
            dialogContainer.getChildren().add(DialogBox.getEchobotErrorDialog(e.getMessage()));
        }
        userInput.clear();
    }
}
