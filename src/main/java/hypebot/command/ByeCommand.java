package hypebot.command;

import java.io.IOException;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;
import hypebot.ui.UiResponse;

/**
 * Represents the ByeCommand created when user prompts 'bye'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class ByeCommand extends Command {
    /**
     * Creates a new ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Triggers UiCli to output message that tasks are being saved to user,
     * then triggers StorageManager to save the current Tasklist to the user's local computer.
     * If file to save not found, triggers UiCli to show error message.
     * Finally, triggers UiCli to output exit message.
     *
     * @param tasks          Tasklist containing Tasks.
     * @param uiCli          User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        try {
            storageManager.save(tasks);
            return uiCli.showExit();
        } catch (IOException e) {
            return uiCli.showError(e.getMessage());
        }
    }
}
