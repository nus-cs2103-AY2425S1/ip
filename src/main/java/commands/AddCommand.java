package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command to add a new task to the task list.
 * The AddCommand class implements the Command interface and handles the addition of a task.
 */
public class AddCommand implements Command {

    private final String description;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param description the description of the task to be added
     */
    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add task command, adding a new task to the task list.
     * The task list is then saved to storage, and a line separator is printed.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master) {
        master.addTask(this.description);
        storage.saveList(master.getTasks());
        UI.printLine();
        return false;
    }
}
