package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.task.Task;

/**
 * Represents a command to unmark a specific task.
 */
public class UnmarkCommand extends Command {

    String indexString;

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
     * @param ui MoiMoi's user interface.
     * @throws InvalidIndexException If the specified task index is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            task.unmark();
            ui.showCompletionMessage("Oof, it's OK! Let's get it done soon ;)\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
