package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 * Represents the UnmarkCommand created when user prompts 'list'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class ListCommand extends Command {
    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Triggers Ui to list out Tasks in Tasklist in numerical insertion order.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showListingTasks(tasks);
    }
}
