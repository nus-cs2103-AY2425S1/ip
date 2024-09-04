package tina;

/**
 * The Tina class represents the main entry point of the chatbot application.
 * It initializes the necessary components such as storage, task list, and UI,
 * and controls the flow of the program.
 */
public class Tina {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Tina instance with the specified file path.
     *
     * @param filePath The path to the file where task data is stored.
     *                 This should not be null or empty.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    public Tina(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);

        assert storage != null : "Storage initialization failed";
        assert tasks != null : "TaskList initialization failed";
    }


    /**
     * Starts the chatbot application by greeting the user, processing tasks,
     * and then exiting the program.
     */
    public void run() {
        assert ui != null : "UI should be initialized before running Tina";

        ui.greet();
        tasks = new TaskList(storage);

        assert tasks != null : "TaskList should be re-initialized in run method";

        ui.doTask(tasks);
        ui.exit();
    }

    /**
     * Processes the user's input and generates a response.
     *
     * @param input The user's input. This should not be null.
     * @return The chatbot's response based on the input.
     * @throws IllegalArgumentException if input is null.
     */

    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        assert ui != null : "UI should be initialized before getting a response";
        assert tasks != null : "TaskList should be initialized before processing input";

        return ui.runInput(tasks, input);
    }


    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        assert args != null : "Arguments should not be null";
        new Tina("./data/tina.txt").run();
    }
}
