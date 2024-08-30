package talkie;

import talkie.command.Command;
import talkie.components.Parser;
import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieException;
import talkie.task.TaskList;

/**
 * The main class for the Talkie application.
 * <p>
 * The {@code Talkie} class initializes the user interface, task list, and storage components. It handles loading and
 * saving tasks, and manages the main program loop where user commands are processed.
 * </p>
 */
public class Talkie {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a {@code Talkie} instance with the specified file path for storage.
     * <p>
     * Initializes the user interface, storage, and task list. Loads existing tasks from storage if available, otherwise
     * initializes an empty task list. Handles any exceptions related to loading data.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Talkie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.loadData());
        } catch (TalkieException e) {
            ui.showTalkieException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main Talkie program loop.
     * <p>
     * Displays the welcome message, reads user commands, and processes them until an exit command is issued. Handles
     * any exceptions related to command execution and displays appropriate messages to the user.
     * </p>
     */
    public void runTalkie() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readCommand();
                Command c = Parser.getCommand(input);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (TalkieException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * The main entry point for the Talkie application.
     * <p>
     * Initializes a {@code Talkie} instance with a specified file path and starts the Talkie program loop.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Start of talkie.Talkie
        new Talkie("./data/talkie.Talkie.txt").runTalkie();
    }
}
