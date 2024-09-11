package hypebot.command;

import java.io.IOException;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

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
     * Returns whether the Command is a command that causes termination of HypeBot.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Triggers Ui to output message that tasks are being saved to user,
     * then triggers StorageManager to save the current Tasklist to the user's local computer.
     * If file to save not found, triggers Ui to show error message.
     * Finally, triggers Ui to output exit message.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        try {
            ui.showSavingTasks();
            storageManager.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showExit();
        }
    }
}
