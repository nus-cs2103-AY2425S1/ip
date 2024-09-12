package stan;

import java.util.ArrayList;

import stan.commands.Command;
import stan.exceptions.StanException;
import stan.ui.Ui;

/**
 * The main class for the Stan chatbot.
 * Manages the initialization of the application, including loading tasks from storage,
 * processing user commands, and saving tasks to storage.
 */
public class Stan {
    private static final String FILE_PATH = "data/stan.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Stan.
     * Initializes the UI, storage, and task list.
     *
     * @param filePath The file path for the task storage file.
     */
    public Stan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(new ArrayList<>(storage.loadTasks()));
    }
    public String getWelcomeMessage() {
        Ui ui = new Ui();
        return ui.showWelcome();
    }
    /**
     * Generates a response to the user input.
     *
     * @param input The user input string.
     * @return The chatbot's response string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (StanException e) {
            return ui.showError(e.getMessage());
        }
    }
    public boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }
    /**
     * Runs the main logic of the Stan chatbot.
     * Continuously reads user commands, processes them, and executes the appropriate actions.
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
            } catch (StanException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Stan chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Stan(FILE_PATH).run();
    }
}
