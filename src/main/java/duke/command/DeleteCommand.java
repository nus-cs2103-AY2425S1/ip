package duke.command;

import duke.Archive;
import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    /**
     * The item number of the task to delete.
     */
    private int itemNum;

    /**
     * Constructor for a delete command.
     *
     * @param itemNum The item number of the task to delete.
     */
    public DeleteCommand(int itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * Deletes the task from the list of tasks and storage, and writes the response message.
     *
     * @param tasks The list of tasks to delete the task from.
     * @param ui The ui to write the response message.
     * @param storage The storage to delete the task from.
     * @param archive The archive for the command to interact with.
     * @throws BobException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws BobException {
        storage.delete(this.itemNum);
        Task task = tasks.delete(this.itemNum);
        ui.delete(task, tasks.size());
    }
}
