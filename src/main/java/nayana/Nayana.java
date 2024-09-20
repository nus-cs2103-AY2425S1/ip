package nayana;
import nayana.command.Command;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It manages user interaction, command processing, and task management.
 */
public class Nayana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui; // Handles user interface operations.

    private boolean isExit = false;

    /**
     * Constructs a new Nayana instance and initializes the user interface.
     */
    public Nayana() {
        this.ui = new Ui();
    }

    /**
     * Sets the file path for the storage and initializes the task list by loading data from the specified file.
     *
     * @param filepath The path of the file to load tasks from.
     */
    public void setFilePath(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NayanaException e) {
            ui.showError("no correct file in " + filepath);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the user interface instance associated with this class.
     *
     * @return The Ui instance.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Parses and executes the given command.
     * If the command leads to an error, the appropriate error message is shown via the UI.
     *
     * @param command The input command to parse and execute.
     */
    public void parseCommand(String command) {
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage); // Executes the command, modifying tasks and UI as necessary.
            isExit = c.isExit();
        } catch (NayanaException e) {
            ui.showError(e.getMessage());
        }
    }
}
