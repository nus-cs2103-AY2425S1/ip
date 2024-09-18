package ip.derrick;

import java.util.ArrayList;

/**
 * Main class to execute the chatbot
 */
public class Derrick {
    private final ArrayList<Task> toDo = new ArrayList<>();
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes the class with Ui, Parser, Storage, and TaskList objects.
     */
    public Derrick() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasksFromFile());

        // Ensure essential components are initialized
        assert tasks != null : "TaskList should be initialized and not null";
        assert storage != null : "Storage should be initialized and not null";
    }

    /**
     * Runs the chatbot, processing the user's command input.
     *
     * @param input The user’s input command as a String.
     */
    public void run(String input) {
        // Retrieve and execute the parsed command
        CommandHandler command = parser.returnCommand(input);
        command.execute(input, tasks, storage, ui);
    }
}
