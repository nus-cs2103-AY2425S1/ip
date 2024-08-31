package mortalreminder.io;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mortalreminder.MortalReminder;
import mortalreminder.backend.Processor;
import mortalreminder.backend.TaskList;
import mortalreminder.commands.Command;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Darius.png"));
    private Image mortalReminderImage = new Image(this.getClass().getResourceAsStream("/images/MortalReminder.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setMortalReminder(MortalReminder mortalReminder) {
        this.mortalReminder = mortalReminder;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Processor processor = mortalReminder.getProcessor();
        TaskList taskList = mortalReminder.getTaskList();
        String input = userInput.getText();
        Command command = Parser.parseInputFromUser(input);
        String response = processor.handleCommand(command, taskList);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMortalReminderDialog(response, mortalReminderImage)
        );
        userInput.clear();
    }

    /**
     * Displays the startup message to be used by the chatbot.
     */
    public void onStartUp() {
        String welcome = mortalReminder.welcome();
        dialogContainer.getChildren().add(DialogBox.getMortalReminderDialog(welcome, mortalReminderImage));
    }
}

