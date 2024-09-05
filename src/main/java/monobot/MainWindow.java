package monobot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import monobot.command.Command;
import monobot.exception.MonoBotException;
import monobot.util.Parser;

/**
 * Handles formatting for main GUI page.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MonoBot monoBot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatBox.heightProperty());
    }

    public void setMonoBot(MonoBot mb) {
        monoBot = mb;
        String greeting = monoBot.getUi().getGreeting();
        addDialogBox(greeting, false);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.trim().isEmpty()) {
            addDialogBox(input, true);

            try {
                Command command = Parser.parseCommand(input);
                command.execute(monoBot.getTasks(), monoBot.getUi(), monoBot.getStorage());
                String response = monoBot.getUi().getLastOutput();
                addDialogBox(response, false);
            } catch (MonoBotException e) {
                addDialogBox(e.getMessage(), false);
            }

            userInput.clear();
        }
    }

    private void addDialogBox(String message, boolean isUser) {
        DialogBox dialogBox = new DialogBox(message, isUser);
        dialogBox.prefWidthProperty().bind(chatBox.widthProperty().subtract(20));
        chatBox.getChildren().add(dialogBox);
    }
}
