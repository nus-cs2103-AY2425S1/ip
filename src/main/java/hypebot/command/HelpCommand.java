package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the UnmarkCommand created when user prompts 'help'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class HelpCommand extends Command {
    /**
     * Creates a new HelpCommand().
     */
    public HelpCommand() {
        super();
    }

    /**
     * Triggers UiCli to output help message showing all possible commands executable by HypeBot.
     *
     * @param tasks Tasklist containing Tasks.
     * @param uiCli User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public String execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        return uiCli.showHelpMessage();
    }
}
