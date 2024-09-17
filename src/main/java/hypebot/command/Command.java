package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;
import hypebot.ui.UiResponse;

/**
 * Represents the base Command class which all Commands inherit from.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public abstract class Command {
    /**
     * Base method implemented by all children of Command,
     * specifies manipulations on Tasklist, UiCli, or StorageManager
     * of HypeBot according to type of Command.
     *
     * @param tasks          Tasklist containing Tasks.
     * @param uiCli          User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    public abstract UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager);
}
