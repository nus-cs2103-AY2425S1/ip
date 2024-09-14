package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the DeleteAllCommand created when user prompts 'deleteall'
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class DeleteAllCommand extends Command {
    /**
     * Creates a new DeleteAllCommand.
     */
    public DeleteAllCommand() {
        super();
    }

    /**
     * Triggers Tasklist to delete all Tasks, then triggers UiCli to show all tasks deleted.
     *
     * @param tasks          Tasklist containing Tasks.
     * @param uiCli          User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.deleteAll();
        uiCli.showDeletedAllTasks();
    }
}
