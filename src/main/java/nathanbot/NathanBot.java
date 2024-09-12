package nathanbot;
/**
 * This code has been refined with the assistance of Copilot to comply with the Java Style Guide.
 * For further information, please refer to the Academic Declarations.
 */

import nathanbot.storage.Storage;
import nathanbot.tasks.TaskListStore;
import nathanbot.ui.UserInterface;

/**
 * The main class for the NathanBot application.
 * Initializes the storage, task list, and user interface, and starts the application.
 */
public class NathanBot {

    private UserInterface ui;

    /**
     * Constructor for NathanBot.
     */
    public NathanBot() {
        Storage storage = new Storage("./data", "./data/TaskList.txt", "./data/TaskList.dat");
        TaskListStore taskList = new TaskListStore(storage);
        this.ui = new UserInterface(taskList);
    }

    /**
     * The main method to run the NathanBot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        NathanBot bot = new NathanBot();
        bot.ui.start();
    }

    public String processInput(String input) {
        return ui.processInput(input);
    }
}
