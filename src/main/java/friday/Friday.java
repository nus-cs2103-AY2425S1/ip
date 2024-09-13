package friday;

import commands.Command;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * The main class that runs the Friday.Friday application, a to-do list chatbot.
 * The Friday.Friday class initializes the necessary components (Storage, TaskList, and UI)
 * and manages the application's main loop, handling user input and executing commands.
 */
public class Friday {

    private final Storage storage;
    private final TaskList master;
    private final UI ui;

    /**
     * Constructs a Friday.Friday object with the specified file path for storage.
     * Initializes the Storage, TaskList, and UI components.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Friday(String filePath) {
        this.storage = new Storage(filePath);
        this.master = new TaskList(this.storage.initList());
        this.ui = new UI();
    }

    /**
     * Runs the main loop of the Friday.Friday application.
     * This method initializes the UI, processes user input, and executes commands
     * until the user issues a command to exit the application.
     * Upon termination, it saves the task list to the storage file.
     */
    public void run() {
        this.ui.init();
        boolean bye = false;

        while (!bye) {
            System.out.print("Your input > ");
            String[] parsed = this.ui.getInput();
            Command command = Parser.parse(parsed);
            boolean isBye = command.execute(this.storage, this.master);
            if (isBye) {
                bye = true;
            }
        }

        this.ui.terminate();
        this.storage.saveList(this.master.getParent());
    }

    /**
     * The main entry point of the Friday.Friday application.
     * Creates a new Friday.Friday instance with the specified file path and runs it.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Friday("./data/friday.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Friday heard: " + input;
    }
}
