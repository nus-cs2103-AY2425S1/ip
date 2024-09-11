package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

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
     * Triggers Ui to output greeting message.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showGreeting();
    }
}
