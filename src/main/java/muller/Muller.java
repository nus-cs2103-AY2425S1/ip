package muller;

import muller.command.Command;
import muller.command.MullerException;
import muller.parser.Parser;
import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * The main class for the Muller application. This class initializes
 * the UI, storage, and task list, and controls the main application flow.
 */
public class Muller {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructor for the Muller class.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Muller(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MullerException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the application, processing user commands
     * until the exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser p = new Parser();
                Command c = p.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MullerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * The main method to start the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Muller("data/muller.txt").run();
    }
}
