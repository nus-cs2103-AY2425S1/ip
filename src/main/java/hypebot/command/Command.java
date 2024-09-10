package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 * Represents the base Command class which all Commands inherit from.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public abstract class Command {
    /**
     * Returns whether the Command is a command that causes termination of HypeBot.
     *
     * @return False (except for child ByeCommand).
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Base method implemented by all children of Command,
     * specifies manipulations on Tasklist, Ui, or StorageManager
     * of HypeBot according to type of Command.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    public abstract void execute(Tasklist tasks, Ui ui, StorageManager storageManager);
}
