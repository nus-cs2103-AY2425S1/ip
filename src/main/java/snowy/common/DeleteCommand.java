package snowy.common;

import snowy.data.SnowyException;

/**
 * Represents a command to delete a task from the task list.
 *
 * The DeleteCommand class allows the user to remove a task from the task list by specifying
 * its index. When executed, it deletes the task at the given index and returns a result
 * indicating that the task was deleted.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task at the specified index from the task list.
     *
     * @return a CommandResult indicating the task has been deleted
     * @throws SnowyException if the index is invalid or the task cannot be deleted
     */
    @Override
    public CommandResult execute() throws SnowyException {
        String str = taskList.deleteTask(index);
        return new CommandResult(str + "\nDeleted task at index " + index);
    }
}
