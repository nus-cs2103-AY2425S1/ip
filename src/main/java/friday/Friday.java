package friday;

import friday.command.Command;
import friday.util.Parser;
import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
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
                isExit = c.shouldExit();
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

    /**
     * Generates a response to the user input.
     *
     * @param input The user input as a string.
     * @return The response from the Friday application.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            if (input.equals("hi")) {
                return ui.greet();
            } else {
                Command c = Parser.parse(input);
                String response = c.execute(tasks, ui, storage);
                if (c.shouldExit()) {
                    return response + "\nEXIT";
                }
                return response;
            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
