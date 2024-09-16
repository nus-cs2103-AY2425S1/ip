import dave.Dave;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * This class handles user input, interacts with the Dave instance, and updates the user interface.
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

    private Dave dave;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukeImage.png"));

    /**
     * Initializes the MainWindow by setting up the scroll pane behavior and ensuring
     * that new content is automatically scrolled into view.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null"; // Ensure scrollPane is initialized
        assert dialogContainer != null : "DialogContainer should not be null"; // Ensure dialogContainer is initialized

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        dialogContainer.setFillWidth(true);
    }

    /**
     * Injects the Dave instance into the controller to enable interaction with the task manager.
     *
     * @param d The Dave instance that handles task management and processes user commands.
     */
    public void setDave(Dave d) {
        assert d != null : "Dave instance should not be null"; // Ensure a valid Dave instance is passed
        this.dave = d;
    }

    /**
     * Handles the user input when the user presses enter or clicks the "Send" button.
     * It creates dialog boxes for both the user's input and Dave's response,
     * and adds them to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.isEmpty() : "User input should not be null or empty"; // Ensure input is valid

        String response = dave.getResponse(input);
        assert response != null : "Response from Dave should not be null"; // Ensure response is valid

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear(); // Clear the input field after handling
    }

    /**
     * Displays a welcome message to the user when the application starts.
     * This message is shown in a dialog box with a list of available commands.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Welcome to Dave!\n"
                + "Here are the missions we accept:\n"
                + "- todo <task description>: Add a ToDo task\n"
                + "- deadline <task description> /by <date>: Add a Deadline task\n"
                + "- event <task description> /from <start time> /to <end time>: Add an Event task\n"
                + "- list: View all tasks\n"
                + "- mark <task number>: Mark a task as done\n"
                + "- unmark <task number>: Unmark a task\n"
                + "- delete <task number>: Delete a task\n"
                + "- find <keyword>: Find tasks that match the keyword\n"
                + "- bye: Exit the program\n"
                + "- reminder all: Check undone tasks\n"
                + "- reminder overdue: Check overdue undone tasks\n";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }
}
