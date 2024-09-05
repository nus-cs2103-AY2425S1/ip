package niko.main;

import java.io.IOException;
import java.util.ArrayList;

import niko.command.Command;
import niko.common.NikoException;
import niko.task.Task;
import niko.task.TaskList;

/**
 * Represents the main Niko chatbot application.
 * It handles the initialization, execution of commands, and termination of the chatbot.
 */
public class Niko {

    /** The storage to read and write tasks. */
    private Storage storage;

    /** The list of tasks managed by the chatbot. */
    private final TaskList taskList;

    /** The UI to interact with the user. */
    private final Ui ui;

    /** The main window for interacting with the GUI. */
    private MainWindow mainWindow;

    /**
     * Constructs a Niko chatbot with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Niko(String filePath) {
        this.taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tasks = storage.load();
            for (Task task : tasks) {
                taskList.addTask(task);
            }
            ui.showLoadSuccessMessage(tasks.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the MainWindow instance for interaction between Niko and the GUI.
     *
     * @param mainWindow The MainWindow instance.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Processes the user input and provides a response.
     * This method is triggered by user input in the GUI.
     *
     * @param input The user's input command.
     */
    public void processUserInput(String input) {
        try {
            // Parse and execute the command
            Command command = Parser.parse(input);
            String response = command.execute(taskList, ui, storage);

            // Show the user input and Niko's response in the MainWindow
            mainWindow.showDialog(input, response);

            // Exit if the command is an exit command
            if (command.isExit()) {
                ui.showGoodbyeMessage();
            }
        } catch (NikoException e) {
            // Handle any Niko-specific exceptions and show the error message
            String response = ui.showErrorMessage(e.getMessage());
            mainWindow.showDialog(input, response);
        }
    }
}
