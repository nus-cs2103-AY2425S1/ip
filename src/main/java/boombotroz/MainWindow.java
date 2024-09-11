package boombotroz;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Contains controller aspect of main GUI.
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

    private Boombotroz boombotroz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image boomImage = new Image(this.getClass().getResourceAsStream("/images/Boom.png"));

    /**
     * Intialises chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Boombotroz instance.
     */
    public void setBoom(Boombotroz b) throws FileNotFoundException {
        boombotroz = b;
        dialogContainer.getChildren().addAll(
                DialogBox.getBoomDialog(String.format("Hello! I am Boombotroz, your personal helper!\n"
                        + "\nCommands Available:\n"
                        + "************************\n"
                        + "CREATE TASK\n"
                        + "------------------------\n"
                        + "1. todo (task) /prior (number)"
                        + "\nCreates TODO typed task with corresponding priority level\n"
                        + "\n2. deadline (task) /by (time) /prior (number)"
                        + "\nCreates DEADLINE typed task with corresponding priority level\n"
                        + "Recognised date format is (YYYY-MM-DD)\n"
                        + "\n3. event (task) /from (timeStart) /to (timeEnd) /prior (number):"
                        + "\nCreates EVENT typed task with corresponding priority level\n"
                        + "Recognised date format is (YYYY-MM-DD)\n"
                        + "------------------------\n"
                        + "MODIFY TASK\n"
                        + "------------------------\n"
                        + "1. mark (number)"
                        + "\nMarks the task at that number\n"
                        + "\n2. unmark (number)"
                        + "\nUnmarks the task at that number\n"
                        + "\n3. delete (number)"
                        + "\nDeletes the task at that number\n"
                        + "------------------------\n"
                        + "MISCELLANEOUS\n"
                        + "------------------------\n"
                        + "1. list"
                        + "\nList all the task at you have at the moment\n"
                        + "\n2. find (word)"
                        + "\nFinds the tasks with exact word match\n"
                        + "************************\n"
                        + "Here is a list of all your current task:\n%s", b.printTaskList()), boomImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Boombotroz's reply.
     * Appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = boombotroz.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBoomDialog(response, boomImage)
        );
        userInput.clear();
    }
}
