package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidIndexException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

/**
 * Represents a command to delete a specific task.
 */
public class DeleteCommand extends Command {

    private String indexString;

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
     * @return Completion message.
     * @throws MoiMoiException If the specified task index is invalid,
     *                         or an error related to storage I/O occurs during task list saving.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws MoiMoiException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);

            tasks.delete(index);
            storage.save(tasks);
            return "Aju nice! I've got rid of this task: " + task.stringUI()
                    + "\nWe have " + tasks.getSize() + " tasks left in the bag~";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
