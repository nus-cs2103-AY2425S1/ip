package Gary;

import java.io.FileNotFoundException;

import Gary.command.Command;

/**
 * The {@code Gary} class represents the main program that runs the task management application.
 * It handles the initialization of the necessary components and manages the main program loop.
 */
public class Gary {
    // Storage object for saving and loading tasks
    protected Storage storage;
    // TaskList object for managing tasks
    protected TaskList taskList;
    // Ui object for interacting with the user
    protected Ui ui;

    /**
     * Constructs a {@code Gary} object with the specified file path for storing tasks.
     * Initializes the {@code Ui}, {@code Storage}, and {@code TaskList} objects.
     *
     * @param filePath The file path where tasks are stored and loaded from.
     */
    public Gary(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.loadTaskList());
        } catch (FileNotFoundException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the main program loop. This method greets the user, reads user input,
     * processes commands, and handles any exceptions that occur during execution.
     */
    public void start() {
        boolean isBye = false;
        this.ui.greet();
        while (!isBye) {
            try {
                String commandInput = this.ui.read();
                Command command = Parser.parse(commandInput);
                command.execute(this.taskList, this.ui, this.storage);
                isBye = command.isBye();
            } catch (GaryException garyException) {
                this.ui.showError(garyException.getMessage());
            }
        }
    }

    /**
     * The main entry point of the application. Initializes a new {@code Gary} object
     * and starts the program.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Gary("src/textFile/gary.txt").start();
    }
}
