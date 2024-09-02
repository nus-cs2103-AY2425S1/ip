package darkpool;

import darkpool.command.Command;
import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;
import darkpool.util.Parser;

/**
 * The main class for the Darkpool application.
 * It initializes the necessary components and handles the main program loop.
 */
public class Darkpool {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Darkpool object.
     *
     * @param filePath The file path to load and store task data.
     */
    public Darkpool(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DarkpoolException e) {
            // Show error message if loading data fails
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Darkpool application.
     * It reads user commands and executes them until the exit command is given.
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.upperLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DarkpoolException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.lowerLine();
            }
        }
    }

    /**
     * The main method to start the Darkpool application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Darkpool("data/Darkpool.txt").run();
    }
}