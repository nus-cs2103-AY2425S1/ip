package spiderman;

/**
 * Represents the main class for the Spiderman application,
 * which manages tasks with functionalities like adding, deleting, and listing tasks.
 */
public class Spiderman {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a Spiderman object, initializing UI, storage, and task list.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Spiderman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromStorage());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main logic of the application, which involves user interaction
     * through command-line inputs.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Parser.parseInput(fullCommand, tasks);
                isExit = Parser.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
        storage.saveTasksToStorage(tasks.getTasks());
        ui.close();
    }

    /**
     * The main entry point for the Spiderman application without GUI.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Spiderman("tasks.txt").run();
    }

    /**
     * Gets the welcome message of the application for GUI.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return "Hello! This is your friendly neighbourhood Spiderman.\n"
                + "What can I do for you?";
    }

    /**
     * Generates a response for the user's chat message in GUI.
     *
     * @param input The user input string.
     * @return The response to the user's input.
     */
    public String getResponse(String input) {
        String response = Parser.parseInput(input, tasks);
        boolean isBye = Parser.isExit();
        if (isBye) {
            storage.saveTasksToStorage(tasks.getTasks());
            this.isExit = true;
        }
        return response;
    }

    /**
     * Checks if the application should exit the GUI.
     *
     * @return True if the application should exit, otherwise false.
     */
    public boolean checkIsExit() {
        return this.isExit;
    }
}
