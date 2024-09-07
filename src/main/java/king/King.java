package king;

import king.commands.Command;

/**
 * The main class for the King application. It handles initialization, task storage, and user interaction.
 */
public class King {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the King application with the specified file path for storing tasks.
     * Loads tasks from the file, if available. If there is an issue loading the tasks,
     * initializes an empty task list.
     *
     * @param filePath The file path to load and store tasks.
     */
    public King(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KingException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the King application, displaying a welcome message and processing user commands
     * until the user exits the program. If any errors occur while processing a command,
     * an error message is displayed.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KingException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new King("data/King.txt").run();
    }
}
