package juno.ui;

import java.util.ArrayList;
import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import juno.command.Command;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.parser.CommandParser;
import juno.task.Task;

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
    private JunoUi junoUi;
    // file manager to handle all file related method calls
    private FileManager fileManager;
    private CommandParser commandParser;
    // task manager to handle all the task related method calls
    private TaskManager taskManager;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/UserImage.png")));
    private final Image junoImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/JunoImage.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Juno instance */
    public void setField() {
        // start the UI
        this.junoUi = new JunoUi();
        this.fileManager = new FileManager(dialogContainer, junoImage);
        this.commandParser = new CommandParser();
        try {
            this.fileManager.ensureFileExist();
            ArrayList<Task> storedTasks = this.fileManager.readTasksFromFile();
            this.taskManager = new TaskManager(storedTasks);
        } catch (TaskManagerException | RuntimeException e) {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(e.getMessage(), junoImage));
        }
    }

    public void displayWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(this.junoUi.displayWelcomeMessage(), junoImage));
    }

    /**
     * Detects user input through the command line.
     * User input is parsed into commands, which are then executed. The loop continues until
     * the user inputs a command "bye" to terminate the chat bot.
     * Creates two dialog boxes, one echoing user input and the other containing Juno's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "User input should not be null";
        String input = userInput.getText().trim();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        try {
            Command command = this.commandParser.parse(input, this.junoUi, this.fileManager, this.taskManager);
            assert command != null : "The command returned should not be null";
            String outputString = command.runCommand();
            assert outputString != null : "The output string returned should not be null";
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(outputString, junoImage));
            if (!command.isInWhileLoop()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        } catch (TaskManagerException e) {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(e.getMessage(), junoImage));
        }
        userInput.clear();
    }
}

