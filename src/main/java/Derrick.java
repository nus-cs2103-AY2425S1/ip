package ip.derrick;

import java.util.ArrayList;


/**
 * Main class to execute the chatbot
 */
public class Derrick {
    private ArrayList<Task> toDo = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the class with an Ui, Parser, Storage, and TaskList object.
     */
    public Derrick() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasksFromFile());

        assert tasks != null : "TaskList should be initialized and not null";
        assert storage != null : "Storage should be initialized and not null";
    }

    /**
     * Runs the chatbot where the user can input his/her commands.
     */
    public void run(String input) {
        CommandHandler c = parser.returnCommand(input);
        c.execute(input, this.tasks, this.storage, this.ui);
    }
}
