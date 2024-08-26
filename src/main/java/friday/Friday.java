package friday;

import friday.command.Command;

/**
 * The main class for the Friday application. It handles the initialization
 * of the UI, storage, and task list, and manages the main loop of the program.
 */
public class Friday {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Friday instance with the specified file path for storage.
     *
     * @param filePath The file path for storing task data.
     */
    public Friday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Friday application, handling user input and
     * executing commands until the user chooses to exit.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Friday application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Friday friday = new Friday("./data/friday.txt");
        friday.run();
    }
}