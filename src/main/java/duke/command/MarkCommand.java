package duke.command;

import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.io.IOException;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    /**
     * The item number of the task to mark.
     */
    private int itemNum;

    /**
     * Whether to mark the task as completed or not completed.
     */
    private boolean isCompleted;

    /**
     * Constructor for a mark command.
     *
     * @param itemNum The item number of the task to mark.
     * @param isCompleted Whether to mark the task as completed or not completed.
     */
    public MarkCommand(int itemNum, boolean isCompleted) {
        this.itemNum = itemNum;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as completed or not completed in the list of tasks and storage,
     * and writes the response message.
     *
     * @param tasks The list of tasks from which to mark the command.
     * @param ui The ui to write the response message.
     * @param storage The storage from which to mark the command.
     * @throws BobException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        try {
            storage.mark(this.itemNum, this.isCompleted);
            Task task = tasks.mark(this.itemNum, this.isCompleted);
            ui.mark(task, this.isCompleted);
        } catch (IOException e) {
            throw new BobException("Sorry, something went wrong when updating your storage.");
        }
    }
}
