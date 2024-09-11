package stobberi;

import stobberi.command.Command;
import stobberi.components.Parser;
import stobberi.components.Storage;
import stobberi.components.TaskList;
import stobberi.stobberiexception.StobberiException;

/**
 * The Stobberi class is the main entry point for the task management application.
 * It initializes the required components, manages the main application loop, and
 * handles user input and task management.
 * <p>
 * The application allows users to manage tasks, save them to a file, and load them
 * upon restart. It provides a simple command-line interface for interacting with tasks.
 * </p>
 *
 * @author Ahmad Syu'aib
 * @version 1.0
 * @since 2024-08-29
 */
public class Stobberi {
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a new Stobberi application instance.
     * Initializes the TaskList, Ui, and Storage components.
     */
    public Stobberi() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/list.txt");
        taskList.updateTaskList(storage.getList());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            assert !c.isExit() : "Exit supposed to return false!";
            return c.execute();
        } catch (StobberiException e) {
            return e.getMessage();
        }

    }

    public void saveList() {
        storage.saveList(taskList.getListOfTasks());
    }

    /**
     * The main method that starts the Stobberi application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {}
}
