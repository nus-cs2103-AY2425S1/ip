package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command to add a new task to the task list.
 * The AddCommand class implements the Command interface and handles the addition of tasks.
 * This class supports adding tasks with one or more descriptive fields.
 */
public class AddCommand implements Command {

    private final String[] description;

    /**
     * Constructs an AddCommand with one or more parts of the task description.
     * This constructor is flexible, allowing for tasks with varying numbers of description elements.
     *
     * @param description an array of strings representing the task description,
     *                    which may contain multiple parts.
     */
    public AddCommand(String... description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new task to the task list.
     * The task description is added to the task list, and the updated task list
     * is saved to persistent storage. This method does not return a specific message but can be extended
     * to provide feedback on the addition if required.
     *
     * @param storage the Storage object used to persistently save the updated task list
     * @param master  the TaskList object containing all tasks, where the new task will be added
     * @return an empty string indicating no direct response to be displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        String response = master.addTask(this.description);
        storage.saveList(master.getTasks());
        return response;
    }
}
