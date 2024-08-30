package mahesh.command;

import mahesh.task.Task;
import mahesh.util.TaskList;
import mahesh.util.Storage;

/**
 * Represents a command to add a task to the TaskList and update the Storage.
 */
public class AddCommand extends Command {
    private TaskList list;
    private Storage store;
    private Task task;

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
     */
    public void execute() {
        list.addToList(task);
        store.updateData(list);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as AddCommand is not an exit command
     */
    public boolean isExit() {
        return false;
    }

}