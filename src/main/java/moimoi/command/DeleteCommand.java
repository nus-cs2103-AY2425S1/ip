package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;

/**
 * Represents a command to delete a specific task.
 */
public class DeleteCommand extends Command {

    String indexString;

    /**
     * Constructs a command to delete the task of specified index.
     *
     * @param indexString Index of task to be deleted.
     */
    public DeleteCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    /**
     * Executes task deletion.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @param ui MoiMoi's user interface.
     * @throws InvalidIndexException If the specified task index is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            tasks.delete(index);
            ui.showCompletionMessage("Aju nice! I've got rid of this task: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks left in the bag~");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
