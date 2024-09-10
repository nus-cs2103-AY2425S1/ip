package mahesh.command;

import java.io.IOException;

import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents a command to delete a task from the TaskList and update the Storage.
 */
public class DeleteCommand extends Command {
    private final TaskList list;
    private final Storage store;
    private final int index;

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
     *
     * @return a String response indicating the result of the deletion
     */
    @Override
    public String execute() {
        String response = list.deleteFromList(index);
        try {
            store.updateData(list);
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
