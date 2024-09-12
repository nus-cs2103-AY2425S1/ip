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

    private static String filePath = "./data/talkie.Talkie.txt";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a {@code Talkie} instance with the specified file path for storage.
     * <p>
     * Initializes the user interface, storage, and task list. Loads existing tasks from storage if available,
     * otherwise initializes an empty task list. Handles any exceptions related to loading data.
     * </p>
     */
    public Talkie() {
        ui = new Ui();
        storage = new Storage(Talkie.filePath);

        // Assert that UI and Storage objects are initialized
        assert ui != null : "UI component should be initialized";
        assert storage != null : "Storage component should be initialized";

        try {
            tasks = new TaskList(this.storage.loadData());

            // Assert that tasks are loaded correctly
            assert tasks != null : "TaskList should be initialized after loading data";

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
        System.out.print(ui.welcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readCommand();
                Command c = Parser.getCommand(input);

                // Assert that the command is not null
                assert c != null : "Command should not be null";

                String response = c.execute(this.tasks, this.ui, this.storage);
                System.out.println(response);
                isExit = c.isExit();
            } catch (TalkieException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     * <p>
     * This method processes a user input string, determines the appropriate command,
     * executes it, and returns the response. If an exception occurs during command
     * execution, the exception message is returned as the response.
     * </p>
     *
     * @param input The user's input string.
     * @return The response generated by executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.getCommand(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (TalkieException e) {
            return e.toString();
        }
    }

    /**
     * The main entry point for the Talkie application.
     * <p>
     * Initializes a {@code Talkie} instance and starts the Talkie program loop.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Start of talkie.Talkie
        new Talkie().runTalkie();
    }
}
