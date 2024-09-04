package streams;

import streams.command.Command;
import streams.exception.StreamsException;
import streams.task.TaskList;
import streams.util.Parser;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Main class for the Streams task management application.
 */
public class streams {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the main Duke application.
     *
     * @param filePath The file path for storing tasks.
     */
    public streams(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StreamsException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (StreamsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new streams("data/tasks.txt").run();
    }
}
