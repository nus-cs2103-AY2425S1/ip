package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 * Represents the UnmarkCommand created when user prompts 'unmark {some index}'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class UnmarkCommand extends Command {
    private int indexOfTaskToUnmark;

    /**
     * Takes in an index (0-indexed) to mark Task in Tasklist as incomplete
     * and creates a new UnmarkCommand.
     *
     * @param idx Index of Task to mark incomplete.
     */
    public UnmarkCommand(int idx) {
        super();
        indexOfTaskToUnmark = idx;
    }

    /**
     * Triggers Tasklist to unmark Task in given index,
     * then prompts Ui to show message informing completion of unmarking.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        tasks.unmark(indexOfTaskToUnmark);
        ui.showUnmarkedTask(tasks.getTaskByIndex(indexOfTaskToUnmark));
    }
}
