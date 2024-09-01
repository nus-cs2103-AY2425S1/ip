package morgana;

import morgana.commands.ByeCommand;
import morgana.commands.Command;
import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Entry point of the Morgana application.
 * Initializes the application and starts the interaction with the user.
 */
public class Morgana {
    private static final String NAME = "Morgana";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Morgana} object with the specified file path for storage.
     * Initializes the {@link Ui}, {@link Storage}, and {@link TaskList} objects,
     * and loads the tasks from the storage file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Morgana(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (MorganaException e) {
            ui.showToUser(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application, which continuously reads user input,
     * parses it into a command, and executes the command. The loop runs until the
     * user issues a {@link ByeCommand}.
     */
    public void run() {
        ui.showWelcomeMessage(NAME);
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.getUserInput();
                Command command = Parser.parse(line);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MorganaException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Morgana("./data/morgana.txt").run();
    }
}
