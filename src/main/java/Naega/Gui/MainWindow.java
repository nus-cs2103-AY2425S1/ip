package Naega.Gui;

import Naega.Task.Task;
import Naega.Task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Naega.*;
import Naega.Ui.Ui;

import java.util.ArrayList;
import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Naega naega;
    private final TaskList taskList = new TaskList();
    private final Ui ui = new Ui();  // Add instance of Ui class

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/IMG_6086.jpg")));
    private final Image naegaImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/IMG_6087.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNaega(Naega naega) {
        this.naega = naega;

        // Perform actions that depend on naega here, after it's initialized
        if (naega.isFirstRun()) {
            String helpMessage = naega.getHelpMessage();
            DialogBox helpDialog = DialogBox.getNaegaDialog(helpMessage, naegaImage);
            dialogContainer.getChildren().add(helpDialog);
        }

        // Display the welcome message
        String welcomeMessage = ui.showWelcome();
        DialogBox welcomeDialog = DialogBox.getNaegaDialog(welcomeMessage, naegaImage);
        dialogContainer.getChildren().add(welcomeDialog);

        String instructions = getInstructions();
        DialogBox instructionDialog = DialogBox.getNaegaDialog(instructions, naegaImage);
        dialogContainer.getChildren().add(instructionDialog);

        try {
            ArrayList<Task> tasks = taskList.getTasks();  // Assuming there's a method like this to load tasks
        } catch (Exception e) {
            showLoadingErrorInGui();  // Display loading error if something goes wrong
        }

    }

    /**
     * Handles user input by sending it to Naega and displaying the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = naega.getResponse(input);

        // Create new dialog boxes for the input and the response
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox naegaDialog = DialogBox.getNaegaDialog(response, naegaImage);

        // Add the dialog boxes to the VBox
        dialogContainer.getChildren().addAll(userDialog, naegaDialog);
        userInput.clear();
    }

    /**
     * Displays a loading error message in the GUI using the Ui class.
     */
    public void showLoadingErrorInGui() {
        String loadingError = ui.showLoadingError();
        DialogBox loadingErrorDialog = DialogBox.getNaegaDialog(loadingError, naegaImage);
        dialogContainer.getChildren().add(loadingErrorDialog);
    }

    private String getInstructions() {
        return """
                Instructions:
                - todo [task]: Adds a todo task.
                - deadline [task] /by yyyy-MM-dd HHmm: Adds a task with a deadline.
                - event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm: Adds an event with a start and end time.
                - list: Displays all tasks.
                - mark [task number]: Marks a task as done.
                - unmark [task number]: Marks a task as not done.
                - delete [task number]: Deletes a task.
                - bye: Exits the app.
                """;
    }
}