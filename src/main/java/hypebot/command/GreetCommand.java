package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the GreetCommand created when HypeBot first starts or user prompts 'start'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class GreetCommand extends Command {
    /**
     * Creates a new GreetCommand();
     */
    public GreetCommand() {
        super();
    }

    /**
     * Triggers UiCli to output greeting message.
     *
     * @param tasks Tasklist containing Tasks.
     * @param uiCli User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        uiCli.showGreeting();
    }
}
