package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 * Represents the DeleteCommand created when user prompts 'delete {some index}'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class DeleteCommand extends Command {
    private int indexOfTaskToDelete;

    /**
     * Takes in an index (0-indexed) to delete Task in Tasklist
     * and creates a new DeleteCommand.
     *
     * @param idx Index of Task to delete from Tasklist.
     */
    public DeleteCommand(int idx) {
        super();
        indexOfTaskToDelete = idx;
    }

    /**
     * Triggers Tasklist to delete Task at given index then triggers Ui to
     * output the deleted task.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        Task removedTask = tasks.delete(indexOfTaskToDelete);
        ui.showDeletedTask(removedTask, tasks);
    }
}
