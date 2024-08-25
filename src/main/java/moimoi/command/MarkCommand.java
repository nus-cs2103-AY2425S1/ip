package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.task.Task;

/**
 * Represents a command to mark a specific task.
 */
public class MarkCommand extends Command {

    String indexString;

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
     * @param ui MoiMoi's user interface.
     * @throws InvalidIndexException If the specified task index is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            task.mark();
            ui.showCompletionMessage("YAY!! One down!!\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
