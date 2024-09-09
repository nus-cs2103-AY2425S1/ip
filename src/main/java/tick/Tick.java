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
     * Constructs a Tick object with the default file path to load the task data from.
     */
    public Tick() {
        this("data/tasks.txt");
    }

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
        } catch (TickException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the response of Tick to the user input.
     * Used for GUI.
     *
     * @param input The user input.
     * @return The response of Tick to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (TickException e) {
            return "ERROR! ERROR!: " + e.getMessage();
        }
    }

    /**
     * Runs the main loop of the Tick application.
     * It continuously reads user commands, parses them, and executes the corresponding actions.
     * The loop terminates when an exit command is issued.
     * Used for CLI.
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
