package GPT;

/**
 * The main class for the GPT application, which handles the initialization of the
 * user interface, storage, and task list, and runs the main application loop.
 */
public class GPT {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new GPT application with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public GPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the main loop of the GPT application, which continuously reads commands
     * from the user, processes them, and handles any exceptions that occur.
     */
    public void run() {
        ui.showWelcomeMessage("GPT");

        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui, storage);

                if (command.isExit()) {
                    break;
                }
            } catch (GPTException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the GPT application. Initializes the application with
     * the specified file path for task storage and starts the main loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new GPT("data/tasks.txt").run();
    }
}
