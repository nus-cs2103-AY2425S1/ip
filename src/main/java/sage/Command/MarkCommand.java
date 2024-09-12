package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Ui;

/**
 * Represents a command to mark or unmark a task.
 */
public class MarkCommand extends Command {

    private final String indexString;
    private final boolean isDone;

    /**
     * Constructs a MarkCommand with the task index and done status.
     *
     * @param indexString The index of the task to mark or unmark.
     * @param isDone      The status to mark the task as (true for done, false for not done).
     */
    public MarkCommand(String indexString, boolean isDone) {
        this.indexString = indexString;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command by marking or un-marking the task.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object for saving changes to the file.
     * @throws SageException If the provided task index is invalid or cannot be parsed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        int index;
        try {
            index = Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SageException("Invalid mark/unmark command. Index must be a number.");
        }

        if (index < 0 || index >= tasks.getSize()) {
            throw new SageException("Invalid task number.");
        }

        return tasks.markTask(index, isDone);
    }
}
