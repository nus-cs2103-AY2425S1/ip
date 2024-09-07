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
            tasks = new TaskList(storage.loadTasks());
        } catch (MullerException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (MullerException e) {
            return ui.showError(e.getMessage());
        }
    }
    /**
     * The main method to start the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Muller("data/muller.txt");
    }
}
