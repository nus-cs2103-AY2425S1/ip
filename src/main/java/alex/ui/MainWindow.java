package alex.ui;

import alex.Alex;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Scanner;

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

    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setAlex(Alex a) {
        alex = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public String scan(String userInput) {

        if (userInput.equalsIgnoreCase("bye")) {
            return alex.getUi().byeMessage();
        }
        else if (userInput.equalsIgnoreCase("list")) {
            alex.getTasks().handleList();

        } else if (userInput.startsWith("mark")) {
            alex.getTasks().handleMark(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("unmark")) {
            alex.getTasks().handleUnmark(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("todo")) {
            alex.getTasks().handleTodo(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("deadline")) {
            alex.getTasks().handleDeadline(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("event")) {
            alex.getTasks().handleEvent(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("delete")) {
            alex.getTasks().handleDelete(userInput);
            alex.getStorage().saveTasksToFile(alex.getFilePath());

        } else if (userInput.startsWith("tasks on")) {
            alex.getTasks().handleDate(userInput);

        } else if (userInput.startsWith("find")) {
            alex.getTasks().handleFind(userInput);

        } else {
            System.out.println("Sorry, I don't understand that command. Did you make a typo?");

        }
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alex.getResponse(input);
        String reply = scan(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
