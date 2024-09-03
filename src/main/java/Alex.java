import Alex.Command.Command;
import Alex.Exceptions.AlexException;
import Alex.Parser.Parser;
import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Represents the main application class that runs the Alex chatbot.
 * Handles initialization, user interactions, and command processing.
 */
public class Alex {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Alex chatbot with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Alex(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlexException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot, processing user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AlexException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Alex("data/tasks.txt").run();
    }
}
