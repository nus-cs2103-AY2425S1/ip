package cancelgpt.gui;

import java.io.IOException;

import cancelgpt.command.Command;
import cancelgpt.core.CancelGpt;
import cancelgpt.exception.command.UnknownInput;
import cancelgpt.exception.task.DeleteTaskInputException;
import cancelgpt.exception.task.FindTaskInputException;
import cancelgpt.exception.task.InvalidTaskException;
import cancelgpt.exception.task.MarkTaskInputException;
import cancelgpt.exception.task.TaskDoesNotExist;
import cancelgpt.exception.task.UnmarkTaskInputException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class GuiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private CancelGpt cancelGpt;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image cancelGptImage = new Image(this.getClass().getResourceAsStream("/images/CancelGpt.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the CancelGpt instance
     */
    public void setCancelGpt(CancelGpt d) {
        cancelGpt = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getCancelGptDialog(cancelGpt.greet(), cancelGptImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CancelGpt's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals(Command.BYE.toString())) {
            handleExit();
        }

        try {
            String response = cancelGpt.handleCommand(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getCancelGptDialog(response, cancelGptImage)
            );
            cancelGpt.saveTasks();
        } catch (MarkTaskInputException | UnmarkTaskInputException | InvalidTaskException
                 | TaskDoesNotExist | UnknownInput | DeleteTaskInputException
                 | FindTaskInputException | IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            showErrorAlert(e);
        }
        userInput.clear();
    }

    /**
     * Handles exiting the application when the user inputs "bye".
     */
    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Goodbye! The application will now exit.");
        alert.showAndWait();

        // Close the JavaFX application
        Platform.exit();
        // Terminate the program
        System.exit(0);
    }

    /**
     * Displays an error alert with a message based on the exception type.
     *
     * @param e the exception that was caught
     */
    private void showErrorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText("An error occurred while processing your input");

        alert.setContentText(e.getMessage());

        // Display the alert and wait for the user to acknowledge it
        alert.showAndWait();
    }
}
