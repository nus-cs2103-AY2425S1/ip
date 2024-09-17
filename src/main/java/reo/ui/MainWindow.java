package reo.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import reo.contacts.ContactList;
import reo.contacts.ContactParser;
import reo.storage.ContactStorage;
import reo.storage.TaskStorage;
import reo.tasks.TaskList;
import reo.tasks.TaskParser;

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

    private TaskList tasks;
    private TaskStorage taskStorage;

    private ContactList contacts;
    private ContactStorage contactStorage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getReoDialog("Welcome! I'm Reo. What can I help you with?", dukeImage)
        );
    }

    /** Injects the required instances.
     *
     * @param tasks The current tasks in the user's list.
     * @param taskStorage The storage object for Tasks.
     *
     * */
    public void setProperties(TaskList tasks, TaskStorage taskStorage, ContactList contacts, ContactStorage contactStorage) {
        this.tasks = tasks;
        this.taskStorage = taskStorage;
        this.contacts = contacts;
        this.contactStorage = contactStorage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getReoDialog(response, dukeImage)
        );
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
            return;
        }
        userInput.clear();
    }

    private String getResponse(String input) {
        TaskParser taskParser = new TaskParser(input, tasks, taskStorage);
        ContactParser contactParser = new ContactParser(input, contacts, contactStorage);
        final String UNKNOWN_COMMAND_ERROR = "ERROR: Unknown command.";
        String taskResponse = taskParser.parse();
        String contactResponse = contactParser.parse();

        if (taskResponse == null && contactResponse == null) {
            return UNKNOWN_COMMAND_ERROR;
        }
        if (taskResponse == null) {
            return contactResponse;
        }
        if (contactResponse == null) {
            return taskResponse;
        }

        return UNKNOWN_COMMAND_ERROR;
    }
}
