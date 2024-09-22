package botimusprime;

import botimusprime.parser.Parser;
import botimusprime.storage.Storage;
import botimusprime.tasks.TaskList;
import botimusprime.ui.Ui;

/**
 * The BotimusPrime class is the main class for the BotimusPrime chatbot.
 * It manages the main components of the chatbot, including storage, UI, task management,
 * and command parsing. This class also contains the main method to start the chatbot.
 */
public class BotimusPrime {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructs a new BotimusPrime instance with the specified file name.
     * Initializes the UI, storage, task list, and parser components.
     *
     * @param fileName The name of the file where tasks are stored.
     */
    public BotimusPrime(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        taskList = storage.loadFromDisk();
        parser = new Parser(taskList, ui, storage);
    }

    /**
     * Returns the TaskList associated with this BotimusPrime instance.
     *
     * @return The TaskList containing all tasks managed by this instance.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

    /**
     * Starts the BotimusPrime chatbot.
     * Displays a greeting message, then enters a loop to read and parse user commands.
     * The loop continues until the user issues an exit command "bye".
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = parser.isBye(fullCommand);
                if (!isExit) {
                    System.out.println(parser.parse(fullCommand));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
        ui.bye();
        ui.closeScanner();
    }

    /**
     * The main method for the BotimusPrime chatbot.
     * Creates a new instance of BotimusPrime and starts the application using the specified
     * task file ("todolist.txt").
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BotimusPrime("todolist.txt").run();
    }
}
