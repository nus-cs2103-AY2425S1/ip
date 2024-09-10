package ollie;

import java.util.Scanner;

import ollie.exception.OllieException;
import ollie.task.TaskList;
import ollie.ui.Ui;

/**
 * The Ollie class runs the Ollie chatbot.
 */
public class Ollie {
    private static final String DATA_FILE_PATH = "./data/ollie.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs an Ollie instance with an existing or new TaskList.
     *
     * @param dataFilePath The file path to the data file, for storing the task list.
     */
    public Ollie(String dataFilePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(dataFilePath);
            this.taskList = new TaskList(storage.loadTasks(), storage);
        } catch (OllieException e) {
            ui.showError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Constructs a new Ollie instance.
     */
    public Ollie() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(DATA_FILE_PATH);
            this.taskList = new TaskList(storage.loadTasks(), storage);
        } catch (OllieException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts Ollie chatbot by displaying a greeting.
     * The chatbot processes user input, and processes commands until it exits.
     */
    public void run() {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }

    /**
     * Parses the user input and returns the response by Ollie.
     */
    public String getResponse(String input) {
        return Parser.parseUserCommand(input, taskList, ui);
    }

    /**
     * Creates a new Ollie instance and runs it.
     *
     * @param args Command line arguments (not used in the application).
     */
    public static void main(String[] args) {
        new Ollie(DATA_FILE_PATH).run();
    }
}
