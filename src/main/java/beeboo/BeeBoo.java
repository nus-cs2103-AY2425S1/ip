package beeboo;

import beeboo.command.Command;
import beeboo.components.Parser;
import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;
import beeboo.exception.InvalidCommandException;

/**
 * The {@code BeeBoo} class represents the main application logic for the BeeBoo task management
 * system. It handles initialization, user interactions, command processing, and task management.
 */
public class BeeBoo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a beeboo instance with the specified file path for storage.
     * Initializes the user interface, storage, and task list. If loading the tasks fails,
     * an empty task list is created.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public BeeBoo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BeeBooExceptions e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the BeeBoo application and enters the main event loop. It shows the welcome message,
     * continuously prompts the user for commands, parses and executes the commands, and handles
     * exceptions. The loop exits when a command that triggers an exit is executed.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        // Prompts user while user doesn't enter bye
        while (!isExit) {
            try {
                String fullCommand = ui.handleCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.chatBox("Invalid Command! Me no understand");
            } catch (BeeBooExceptions e) {
                ui.chatBox(e.toString());
            }
        }
    }

    /**
     * The entry point of the BeeBoo application. Creates a new {@code BeeBoo} instance with a
     * default file path for storage and starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new BeeBoo("./data/beeboo.txt").run();
    }
}
