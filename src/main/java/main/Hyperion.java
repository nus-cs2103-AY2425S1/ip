package main;

import exception.CommandFoundButInvalidException;
import exception.HyperionException;

/**
 * The main class for the Hyperion application.
 * It initializes the user interface, storage, and task list, and processes user input commands.
 */
public class Hyperion {
    public static final String FILE_PATH = "data/tasks.txt";

    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructs a {@code Hyperion} instance with a specified file path for storage.
     * Initializes the user interface, loads tasks from storage, and starts processing user input.
     *
     */
    public Hyperion() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(FILE_PATH);
            this.allTasks = new TaskList(storage.load());

        } catch (CommandFoundButInvalidException e) {
            System.out.print("There is an error" + e.getMessage());
        }
    }

    public static void main(String[] args) {
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input, allTasks, this.storage, this.ui);
            return parser.run();
        } catch (HyperionException e) {
            return e.getMessage();
        }
    }
}
