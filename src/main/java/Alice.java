import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * The entry point to the application.
 * Encapsulates a TaskList for storing tasks, a Storage for storing and loading
 * input data, and a Ui for handling user interaction.
 */
public class Alice {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initialises an instance of Alice chatbot.
     *
     * @param filePath the path of the file to save the tasks to.
     */
    public Alice(String filePath) {
        assert !filePath.isEmpty() : "filePath cannot be empty";
        storage = new Storage(filePath);
        // load tasks
        storage.loadTasks();
        tasks = storage.getTasks();

        // initialise UI with the loaded tasks
        ui = new Ui(tasks);
    }

    public static void main(String[] args) {
        // create instance of Alice with loaded tasks
        new Alice("data.txt").start();
    }

    /**
     * Starts the program.
     *
     * @return the welcome message.
     */
    public String start() {
        return ui.showWelcomeMessage();
    }

    /**
     * Returns Alice's response to the given input.
     *
     * @param input the user's input.
     * @return Alice's response.
     */
    public String response(String input) {
        if (input.equals("bye")) {
            storage.saveTasks(tasks);
        }
        return ui.handleUserInput(input);
    }
}
