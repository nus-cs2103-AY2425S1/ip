package Gary;

import java.io.FileNotFoundException;
import Gary.command.Command;

/**
 * The {@code Gary} class represents the main program that runs the task management application.
 * It handles the initialization of the necessary components and manages the main program loop.
 */
public class Gary {
    protected Storage storage;
    protected TaskList taskLists;
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
            this.taskLists = new TaskList(this.storage.loadTaskList());
        } catch (FileNotFoundException e) {
            this.taskLists = new TaskList();
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
                Command c = Parser.parse(commandInput);
                c.execute(this.taskLists, this.ui, this.storage);
                isBye = c.isBye();
            } catch (GaryException ge) {
                this.ui.showError(ge.getMessage());
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
