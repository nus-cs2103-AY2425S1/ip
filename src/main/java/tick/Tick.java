package tick;

import java.io.IOException;

import tick.commands.Command;
import tick.exceptions.TickException;
import tick.parser.Parser;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

/**
 * Tick is a chatbot that helps users to keep track of their tasks.
 */
public class Tick {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Tick object and initializes the UI, storage, and task list.
     *
     * @param filePath The file path to load the task data from.
     */
    public Tick(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Tick application.
     * It continuously reads user commands, parses them, and executes the corresponding actions.
     * The loop terminates when an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TickException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Tick("data/tasks.txt").run();
    }
}