package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Task.Task;
import sage.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String indexString;

    /**
     * Constructs a DeleteCommand with the task index, to delete, as a string.
     *
     * @param indexString The index of the task to be deleted.
     */
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Executes the delete command, removing the task from the task list.
     * If the index is not a number or is invalid, a SageException is thrown.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object for saving changes to the file.
     * @throws SageException If the index is not a valid number or is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        int index;
        try {
            index = Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SageException("Invalid delete command. Index must be a number.");
        }
        Task deletedTask = tasks.deleteTask(index);
        assert !tasks.getTasks().contains(deletedTask) : "Task should be deleted from the task list.";
        return ui.showDeletedTask(deletedTask, tasks.getSize());
    }
}
