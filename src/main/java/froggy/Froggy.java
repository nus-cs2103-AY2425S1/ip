package froggy;

/**
 * Froggy is a chatbot that keeps track of tasks. It can be run via the terminal with fewer features
 * or interact with users through a GUI.
 * It supports adding, deleting, and managing tasks using commands parsed from user input.
 */
public class Froggy {
    private static final String FILE_PATH = "./data/taskList.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Froggy chatbot instance with the given file path for storing tasks.
     * Loads existing tasks from the specified file path.
     *
     * @param filePath The file path to store the task list.
     * @throws FroggyException If there is an error initializing the storage or loading tasks.
     */
    public Froggy(String filePath) throws FroggyException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Runs the Froggy chatbot in terminal mode. Greets the user, accepts commands, and
     * continues until the user issues an exit command. Upon exiting, tasks are saved.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            Command c = parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        ui.showExit();
        storage.saveTasks(tasks.getTasks());
        ui.close();
    }

    /**
     * Returns the greeting message when the chatbot is initialized.
     *
     * @return A greeting message to the user.
     */
    public String getGreeting() {
        return ui.getGreeting();
    }

    /**
     * Generates a response for the user's chat message. This method is used for handling
     * input in the GUI version of the chatbot.
     *
     * @param input The user's input message.
     * @return A response message from Froggy based on the user's input.
     */
    public String getResponse(String input) {
        Command c = parser.parse(input);
        return c.executeAndGetOutput(tasks, ui, storage);
    }

    /**
     * The main entry point for running Froggy in terminal mode.
     * Initializes the chatbot and starts the interaction loop in the terminal.
     *
     * @param args Command-line arguments, not used.
     */
    public static void main(String[] args) {
        try {
            new Froggy("./data/taskList.txt").run();
        } catch (FroggyException e) {
            System.out.println(e.getMessage());
        }

    }
}
