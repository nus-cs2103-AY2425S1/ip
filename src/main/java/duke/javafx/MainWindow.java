package duke.javafx;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.InvalidInputException;
import duke.parser.InputParser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainWindow class is the main controller for the application's GUI.
 * It manages user interactions, displays the task list, and handles input and output.
 * This class interacts with the {@link TaskList}, {@link Storage}, and {@link Ui}
 * components to facilitate task management and user interface operations.
 * <p>
 * The GUI is built using JavaFX, with components defined in an FXML file.
 * This class is responsible for loading the FXML layout, initializing UI elements,
 * and responding to user input.
 * </p>
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

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a new MainWindow object, which loads and initializes the UI components
     * from the specified FXML file and sets up the main application window.
     *
     * @param stage    The primary stage of the application.
     * @param taskList The task list to be managed by the application.
     * @param storage  The storage component for saving and loading tasks.
     * @param ui       The user interface component for interacting with the user.
     */
    public MainWindow(Stage stage, TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;

        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(this));
        stage.setTitle("My Daily Tasks");
        stage.show();
        stage.setOnCloseRequest(event -> saveTasks()); // Save tasks on exit
    }

    /**
     * Initializes the MainWindow. This method is automatically called after
     * the FXML file has been loaded and is used to perform any necessary setup.
     * It binds the scroll pane's vertical value property to the height property of the dialog container,
     * ensuring that the scroll pane always scrolls to the bottom as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input from the text field. When the user submits input, this method
     * parses the input, executes the corresponding command, and displays the result
     * in the dialog container.
     * <p>
     * If the input is invalid, an error message is printed to the console.
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        try {
            Command command = InputParser.parseUserInput(input);
            String response = command.execute(taskList, ui, storage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
        userInput.clear();
    }

    /**
     * Saves the current state of the task list to a file. This method is
     * called when the application is about to close.
     */
    private void saveTasks() {
        try {
            Storage.saveTasksListToStateFile(this.taskList.getTasks());
            System.out.println("Tasks saved.");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}
