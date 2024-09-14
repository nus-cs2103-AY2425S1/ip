package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the MarkCommand created when user prompts 'mark {some index}'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class MarkCommand extends Command {
    private int indexOfTaskToMark;

    /**
     * Takes in an index (0-indexed) to mark Task in Tasklist as complete
     * and creates a new MarkCommand.
     *
     * @param idx Index of Task to mark complete.
     */
    public MarkCommand(int idx) {
        super();
        indexOfTaskToMark = idx;
    }

    /**
     * Triggers Tasklist to mark Task in given index,
     * then prompts UiCli to show message informing completion of marking.
     *
     * @param tasks Tasklist containing Tasks.
     * @param uiCli User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.mark(indexOfTaskToMark);
        uiCli.showMarkedTask(tasks.getTaskByIndex(indexOfTaskToMark));
    }
}
