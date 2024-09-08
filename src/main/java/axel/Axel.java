package axel;

import java.io.IOException;

/**
 * The {@code Axel} class is the main entry point for the application.
 * It handles the initialization of the necessary components, such as the {@code Ui}, {@code Storage}, and {@code TaskList},
 * and manages the loading of tasks from the storage file.
 */
public class Axel {
    /**
     * The path to the file where tasks are stored.
     */
    private static final String FILE_PATH = "./data/axel.txt";

    /**
     * The storage handler that manages reading from and writing to the task file.
     */
    protected Storage storage;

    /**
     * The list of tasks managed by the application.
     */
    private TaskList tasks;

    /**
     * The user interface handler that manages interactions with the user.
     */
    protected Ui ui;

    /**
     * Initializes a new instance of the {@code Axel} class.
     * Sets up the {@code Ui}, {@code Storage}, and {@code TaskList} components.
     * Attempts to load existing tasks from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Axel(String filePath) {
        ui = new Ui ();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | CorruptedFileException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application.
     * Continuously reads user commands, processes them, and executes the corresponding actions
     * until the user issues a command to exit the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AxelException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public String getResponse(String input) {
        try {
            return Parser.parse(input).execute(tasks, ui, storage);
        } catch (AxelException e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point of the application.
     * Initializes the application and starts the main loop.
     *
     * @param args Command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new Axel(FILE_PATH).run();
    }
}

