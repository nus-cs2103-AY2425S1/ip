package mahesh.command;

import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents a command to delete a task from the TaskList and update the Storage.
 */
public class DeleteCommand extends Command {
    private TaskList list;
    private Storage store;
    private int index;

    /**
     * Constructs a DeleteCommand with the specified TaskList, Storage, and task index.
     *
     * @param list  the TaskList from which the task will be deleted
     * @param store the Storage to update after deletion
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(TaskList list, Storage store, int index) {
        this.list = list;
        this.store = store;
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the task from the TaskList and updating the Storage.
     */
    public void execute() {
        list.deleteFromList(index);
        store.updateData(list);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as DeleteCommand is not an exit command
     */
    public boolean isExit() {
        return false;
    }

}
