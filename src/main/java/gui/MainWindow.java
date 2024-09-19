package gui;
import FRIDAY.FRIDAY;
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

    private String GREETING_MESSAGE = """
                                        Hey there! I'm FRIDAY, your personal digital assistant!
                                        + "Please enter commands in the following format so that I can begin to help you get organized!
                                        + "To add tasks:
                                        + "I. todo <task description>
                                        + "II. event <task description>/from <start time> to <end time>
                                        + "III. deadline <task description>/<due date in YYYY-MM-DD format>
                                        + "Other commands:
                                        + "I. list - displays list of tasks"
                                        + "II. mark/unmark <task number> - marks task as done/undone
                                        + "III. archive - removes all tasks but saves them in an archived file
                                        + "IV. delete <task number> - permanently deletes task
                                        + "V. search <description> - displays list of tasks with matching descriptions
                                        + "VI. bye - exits application and saves task list locally""";
    private FRIDAY FRIDAY;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image FRIDAYImage = new Image(this.getClass().getResourceAsStream("/images/FRIDAY.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getFRIDAYDialog(GREETING_MESSAGE, FRIDAYImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the FRIDAY instance */
    public void setFRIDAY(FRIDAY f) {
        this.FRIDAY = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing FRIDAY's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty()) {
            return;
        }

        String response = FRIDAY.getResponse(input.trim());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFRIDAYDialog(response, FRIDAYImage)
        );
        userInput.clear();
    }
}
