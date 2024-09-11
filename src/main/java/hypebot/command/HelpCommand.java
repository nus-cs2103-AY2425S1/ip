package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

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
     * Triggers Ui to output help message showing all possible commands executable by HypeBot.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showHelpMessage();
    }
}
