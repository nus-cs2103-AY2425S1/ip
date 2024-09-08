package BonnieGUI;

import Exceptions.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private Bonnie Bonnie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mocha.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bonnie.jpeg"));

    // Store username here
    protected static String username;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        GUIStorage.init();
        startChat();
    }

    /** Injects the Duke instance */
    public void setBonnie(Bonnie d) {
        Bonnie = d;
    }

    public void startChat() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Welcome to Bonnie bot. Here are the available commands for you! \n" +
                        "1. list: displays the list of tasks that you have, with the task number\n" +
                        "2. mark/unmark {task number}: marks or unmarks that task as done\n" +
                        "3. todo {task name}: Bonnie adds a todo task into your task list\n" +
                        "4. deadline {task name} /by {YYYY-MM-DD}: Bonnie adds a task with a deadline to your task list.\n" +
                        "5. event {task name} /from {start} /to {end}: Bonnie adds an event with a start/end time to your task list.\n" +
                        "6. delete {task number}: Bonnie deletes the task with that number from your task list.\n" +
                        "7. find {keyword}: Bonnie find all tasks containing that keyword from your task list.\n" +
                        "Bonnie wants to remind you that you should substitute items with curly braces with the actual information.\n" +
                        "Also, do remember to use the forward slashes! \"/from\" is valid but \"from\" is NOT valid!\n" +
                        "Example: \"event clean floor /from 18th September 5pm /to 18th September 6pm\" is a valid command\n", dukeImage)
        );
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Firstly, input your desired name!", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        // If we dont have a name yet, do the setup process
        if (username == null) {
            String input = userInput.getText();
            String response = Bonnie.getInitialisationResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            username = input;
        } else {
            String input = userInput.getText();
            String response = Bonnie.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
