package mahesh.command;

import java.io.IOException;

import mahesh.task.Task;
import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents a command to add a task to the TaskList and update the Storage.
 */
public class AddCommand extends Command {
    private final TaskList list;
    private final Storage store;
    private final Task task;

    /**
     * Constructs an AddCommand with the specified TaskList, Storage, and Task.
     *
     * @param list  the TaskList to which the task will be added
     * @param store the Storage to save the task
     * @param task  the Task to be added
     */
    public AddCommand(TaskList list, Storage store, Task task) {
        this.list = list;
        this.store = store;
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the TaskList and updating the Storage.
     *
     * @return a String response indicating the result of adding the task
     */
    @Override
    public String execute() {
        String response = list.addToList(task);
        try {
            store.updateData(list);
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}