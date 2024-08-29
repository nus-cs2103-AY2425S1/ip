package ouiouibaguette;

import command.Command;
import parser.Parser;
import storage.FileStorage;
import storage.Storage;
import tasklist.TaskList;
import ui.CommandLineUi;

/**
 * The main class for the OuiOuiBaguette application.
 * It initializes the necessary components and handles the main application loop.
 */
public class OuiOuiBaguette {

    /** The storage system used to persist tasks. */
    private Storage storage;

    /** The task list that manages the user's tasks. */
    private TaskList tasks;

    /** The command-line interface for interacting with the user. */
    private CommandLineUi ui;

    /** The parser that interprets user input and converts it into commands. */
    private Parser parser;

    /**
     * Constructs the OuiOuiBaguette application with the specified directory path for storage.
     * Initializes the user interface, storage, task list, and parser.
     *
     * @param dirPath The directory path where task data will be stored.
     */
    public OuiOuiBaguette(String dirPath) {
        ui = new CommandLineUi();
        storage = new FileStorage(dirPath);
        tasks = new TaskList(storage);
        parser = new Parser();
    }

    /**
     * Runs the main loop of the OuiOuiBaguette application.
     * Greets the user, processes commands in a loop, and exits when the user issues an exit command.
     */
    public void run() {
        ui.greet();
        System.out.println();

        boolean isExit = false;

        while (!isExit) {
            String cmd = ui.readCommand();

            try {
                Command c = parser.parseCommand(cmd);
                if (c.isExit()) {
                    break;
                }

                ui.showDivider();

                c.execute(tasks, ui);

                ui.showDivider();

            } catch (OuiOuiBaguetteException e) {
                ui.showDivider();
                ui.speakLine(e.getMessage());
                ui.showDivider();
            }

            System.out.println();
        }

        ui.farewell();
        System.out.println();
    }
}
