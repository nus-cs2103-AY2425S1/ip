package nether;

import javafx.scene.control.Alert;
import nether.command.Command;
import nether.parser.Parser;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * The main class of Nether - a task management chatbot that manages different types of tasks
 * (e.g., to-do, deadline, event tasks).
 *
 * The class initializes the following essential components:
 * - {@link Storage} for reading and writing tasks to a file.
 * - {@link TaskList} for managing the list of tasks.
 * - {@link Ui} for interacting with the user.
 * - {@link Parser} for interpreting user input.
 *
 * The application runs in a loop, taking user input, processing commands, and
 * executing the appropriate actions until an exit command is given.
 */

public class Nether {
    // the file path where task data is stored
    private static final String STORAGE_FILE_PATH = "./data/nether.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private boolean isExit = false;

    /**
     * Constructs a new Nether instance.
     *
     * @param filePath The path to the file used for storing task data.
     */

    public Nether(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the Nether application, producing a welcome message, and processing user commands in a loop.
     * The loop continues until an exit command is issued by the user.
     * It handles user input and executes the appropriate commands.
     */
    public void run() {
        ui.printWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AssertionError ae) {
                // Handle assertion error, e.g., show a dialog box or update status message in GUI
                System.out.println("reached");
                showAlert("Input Error", ae.getMessage());
            } catch (NetherException ne) {
                // Handle other exceptions
                System.out.println("reached");
                showAlert("Command Error", ne.getMessage());
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Command c = parser.parse(input);
            response.append(c.execute(tasks, ui, storage));
            isExit = c.isExit();
            return response.toString();
        } catch (NetherException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method where the program starts.
     * Initializes the application with the specified storage file path and starts it.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Nether(STORAGE_FILE_PATH).run();
    }

}
