package rose;

import java.io.IOException;

import rose.command.Command;

/**
 * The {@code Rose} class is the main entry point for the task management application.
 *
 * <p>This class initializes the necessary components, such as {@link Ui}, {@link Storage},
 * and {@link TaskList}, and orchestrates the application's main loop, handling user commands
 * and interactions.</p>
 */
public class Rose {

    private final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rose() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application.
     *
     * <p>This method displays the opening message and continuously listens for user input,
     * processing commands until the user exits the application. It handles errors and ensures
     * the user interface is updated accordingly after each command.</p>
     */
    public void run() {
        ui.showOpening();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command currentCommand = Parser.parse(fullCommand);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (RoseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Serves as the entry point for the application.
     *
     * <p>This method creates a new instance of {@code Rose} with the specified file path and
     * starts the application by calling the {@link #run()} method.</p>
     *
     * @param args Command-line arguments (not used).
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (RoseException e) {
            return e.getMessage();
        }
    }
    //public static void main(String... args) {
        //new Rose().run();
    //}
}


