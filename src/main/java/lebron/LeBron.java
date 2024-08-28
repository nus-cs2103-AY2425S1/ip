package lebron;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for the LeBron application.
 * This class handles the initialization of the application, including loading tasks,
 * handling user commands, and controlling the application's flow.
 */
public class LeBron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a LeBron application instance with the specified file path for storage.
     * Initializes the storage, user interface, and parser. Attempts to load the task list
     * from the specified file, or initializes an empty task list if loading fails.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public LeBron(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the LeBron application, handling the main loop that processes user input
     * and executes commands until the application is instructed to exit.
     */
    public void run() {
        String input;
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                input = ui.getUserCommand();
                ui.showLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (LeBronException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for the LeBron application.
     * Creates a new instance of the application and runs it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new LeBron("./data/lebron.txt").run();
    }
}
