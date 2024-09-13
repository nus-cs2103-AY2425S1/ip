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

    private Image userImage = new Image(this.getClass().getResourceAsStream("userImage.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("dukeImage.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null"; // Assert that scrollPane is initialized
        assert dialogContainer != null : "DialogContainer should not be null"; // Assert that dialogContainer is initialized

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        dialogContainer.setFillWidth(true);
    }

    /** Injects the Duke instance */
    public void setDave(Dave d) {
        assert d != null : "Dave instance should not be null"; // Assert that a valid Dave instance is passed
        dave = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.isEmpty() : "User input should not be null or empty"; // Assert input is valid

        String response = dave.getResponse(input);
        assert response != null : "Response from Dave should not be null"; // Assert response is valid

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message and available commands in the dialog container when the app starts.
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
                + "- bye: Exit the program\n";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }
}
