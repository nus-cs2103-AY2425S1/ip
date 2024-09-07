package mortalreminder.io;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mortalreminder.MortalReminder;
import mortalreminder.commands.Command;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;

/**
 * Controller for the main GUI.
 */
public class MortalReminderWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MortalReminder mortalReminder;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Darius.png")));
    private final Image mortalReminderImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/MortalReminder.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the MortalReminder instance
     */
    public void setMortalReminder(MortalReminder mortalReminder) {
        this.mortalReminder = mortalReminder;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mortal Reminder's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            Command command = Parser.parseInputFromUser(input);
            response = mortalReminder.executeCommand(command);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMortalReminderDialog(response, mortalReminderImage, command.commandType())
            );
        } catch (MortalReminderException e) {
            response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMortalReminderDialog(response, mortalReminderImage, CommandType.UNKNOWN)
            );
        }
        userInput.clear();
    }

    /**
     * Displays the startup message to be used by the chatbot.
     */
    public void onStartUp() {
        String welcome = mortalReminder.welcome();
        dialogContainer.getChildren()
                .add(DialogBox.getMortalReminderDialog(welcome, mortalReminderImage, CommandType.UNKNOWN));
    }
}

