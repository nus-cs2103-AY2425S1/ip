package mylo.command;

import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Tui;

/**
 * Represents a command to delete a task from the task list by its index.
 * <p></p>
 * <p>This class is responsible for executing the delete operation on the task list,
 * removing a task based on its position in the list.</p>
 * <p></p>
 * <p>If the specified index is out of bounds, an {@code IndexOutOfBoundsException} will be thrown.</p>
 *
 * @author cweijin
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    /**
     * Constructs a {@code DeleteCommand} with the specified index.
     * <p></p>
     * <p>The index corresponds to the position of the task in the task list
     * that is to be removed.</p>
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * <p></p>
     * <p>If the index is invalid, an {@code IndexOutOfBoundsException} will be thrown.</p>
     *
     * @param list The task list from which the task will be deleted.
     * @param tui   The user interface that can display any necessary output after deletion.
     * @throws StorageOperationException If there is an issue with the storage when deleting the task.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds in the task list.
     */
    @Override
    public String execute(TaskList list, Tui tui) throws StorageOperationException, IndexOutOfBoundsException {
        return list.deleteTask(INDEX);
    }
}
