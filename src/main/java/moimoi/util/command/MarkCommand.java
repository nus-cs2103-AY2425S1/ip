package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidIndexException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

/**
 * Represents a command to mark a specific task.
 */
public class MarkCommand extends Command {

    private String indexString;

    /**
     * Constructs a command to mark the task of specified index.
     *
     * @param indexString Index of task to be marked.
     */
    public MarkCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    /**
     * Marks the task of specified index.
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

            task.mark();
            storage.save(tasks);
            return "YAY!! This one's down!!\n" + task.stringUI();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
