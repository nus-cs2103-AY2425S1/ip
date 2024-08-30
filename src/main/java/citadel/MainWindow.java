package citadel;

import citadel.Task.TaskList;
import citadel.ui.TextUI;

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

    /** The main Citadel class that runs Main **/
    private Citadel citadel;

    /** The user interface used to interact with the user. */
    private static final TextUI ui = new TextUI();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Citadel.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendWelcomeMessage();
    }

    /**
     * Sets the Citadel instance.
     */
    public void setCitadel(Citadel c) {
        citadel = c;
    }

    /**
     * Creates a dialog box with a welcome message.
     */
    private void sendWelcomeMessage() {
        String startMessage = ui.printStart();
        Storage db = new Storage("data/citadel");
        TaskList items = db.getTasks();
        String tasksToString = ui.printTasks(items);
        String welcomeMessage = startMessage + "\n"
                                + tasksToString;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Handles the user input and echoes the message along with a response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = citadel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
