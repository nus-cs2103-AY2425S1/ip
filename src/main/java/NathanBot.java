import nathanbot.storage.Storage;
import nathanbot.tasks.TaskList;
import nathanbot.ui.UserInterface;

/**
 * The main class for the NathanBot application.
 * Initializes the storage, task list, and user interface, and starts the application.
 */
public class NathanBot {
    /**
     * The main method to run the NathanBot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Storage storage = new Storage("./data", "./data/TaskList.txt", "./data/TaskList.dat");
        TaskList taskList = new TaskList(storage);
        UserInterface ui = new UserInterface(taskList);
        ui.start();
    }

    
}