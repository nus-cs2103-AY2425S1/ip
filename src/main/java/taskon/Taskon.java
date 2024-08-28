package taskon;

import taskon.commands.Command;
import taskon.exception.TaskonException;
import taskon.parser.Parser;
import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * The main class for the Taskon application.
 * This class handles the initialization of the application, the main loop, and the interaction between the components.
 */
public class Taskon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Taskon object and initializes the application with the specified storage file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Taskon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaskonException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Taskon application. This method handles the main loop where commands are read, parsed, and executed.
     */
    public void run() {
        ui.greet();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TaskonException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method runs the Taskon application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Taskon("./data/taskon.txt").run();
    }
}
