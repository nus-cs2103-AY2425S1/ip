package gui;

import java.util.Objects;

import errorhandling.ReginaException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import regina.Regina;
import tasks.TaskList;

/**
 * Controller for the main GUI of the Regina chatbot application.
 * This class manages user input and displays the dialog container for chat messages and task checkboxes.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer; // To handle user messages
    @FXML
    private TextField userInput; // Input field for user messages
    @FXML
    private Button sendButton; // Button to send messages
    @FXML
    private VBox checkboxContainer; // This VBox will hold dynamically created checkboxes

    private Regina regina; // Reference to the Regina instance

    // Images for user and Regina Chatbot
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private final Image reginaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Regina.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        if (regina != null) {
            dialogContainer.getChildren().addAll(DialogBox.getReginaDialog(regina.greet(), reginaImage));
            loadCheckboxes(); // Load task checkboxes when the main window is initialized
        }
    }

    public void setRegina(Regina r) {
        regina = r; // Set the Regina instance
        if (regina != null) {
            loadCheckboxes(); // Load checkboxes whenever Regina is set
        }
    }

    public void loadCheckboxes() {
        TaskList tasks = regina.getListOfTasks(); // Fetch the task list
        checkboxContainer.getChildren().clear(); // Clear existing checkboxes

        for (int i = 0; i < tasks.size(); i++) { // Iterate through tasks
            String taskDescription = tasks.get(i).toString(); // Get string representation of the task
            CheckBox taskCheckBox = new CheckBox(taskDescription);
            taskCheckBox.setOnAction(e -> {
                System.out.println(taskCheckBox.isSelected() ? taskDescription + " selected" : taskDescription + " deselected");
            });
            checkboxContainer.getChildren().add(taskCheckBox); // Add to the checkbox container
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = regina.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getReginaDialog(response, reginaImage)
        );
        loadCheckboxes();
        // Clear the input field after handler
        userInput.clear();
    }
}
