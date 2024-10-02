package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidIndexException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

/**
 * Represents a command to unmark a specific task.
 */
public class UnmarkCommand extends Command {

    private String indexString;

    /**
     * Constructs a command to unmark the task of specified index.
     *
     * @param indexString Index of task to be unmarked.
     */
    public UnmarkCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    /**
     * Unmarks the task of specified index.
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

            task.unmark();
            storage.save(tasks);
            return "Oof, it's OK! Let's get it done soon ;)\n" + task.stringUI();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
