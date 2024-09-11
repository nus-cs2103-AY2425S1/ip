package controller;

import java.time.format.DateTimeParseException;

import command.Command;
import fridayException.FridayException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UiGui;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private UiGui gui;
    private Storage storage;
    private TaskList tasks;
//    private Ui ui;
//    private Thread cliThread;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the storage for the main window.
     * @param storage The storage to save the task list to file.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    /**
     * Sets the GUI for the main window.
     * @param gui The GUI to display messages to the user.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setGui(UiGui gui) {
        this.gui = gui;
        return this;
    }

    /**
     * Sets the task list for the main window.
     * @param tasks The task list to be modified by the commands.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setTasks(TaskList tasks){
        this.tasks = tasks;
        return this;
    }

//    public MainWindow setUi(Ui ui) {
//        this.ui = ui;
//        return this;
//    }
//
//    public MainWindow setCli(Thread cliThread) {
//        this.cliThread = cliThread;
//        return this;
//    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        try {
            Command command = Parser.parseUserInput(input);
            response = command.executeGui(tasks, gui, storage);
//            if ("bye".equals(input)) {
//                cliThread.interrupt();
//                ui.closeScanner();
//                Platform.exit();
//            }
        } catch (FridayException | DateTimeParseException | IndexOutOfBoundsException e) {
            if (e instanceof DateTimeParseException) {
                response = gui.showError("Please enter a valid date in the format yyyy-mm-dd.");
            } else {
                response = gui.showError(e.getMessage());
            }
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userImage, input),
                    DialogBox.getFridayDialog(dukeImage, response)
            );
            userInput.clear();
        }
    }
}