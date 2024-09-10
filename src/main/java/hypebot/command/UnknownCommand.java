package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 * Represents the UnmarkCommand created when Parser cannot comprehend any command keyword.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class UnknownCommand extends Command {
    private final String unknownCommand;

    /**
     * Takes in the unknown command keyword and creates a new UnknownCommand.
     *
     * @param command Unknown command keyword entered by user, from Parser.
     */
    public UnknownCommand(String command) {
        super();
        unknownCommand = command;
    }

    /**
     * Triggers Ui to show that HypeBot does not know any commands associated with the keyword.
     *
     * @param tasks Tasklist containing Tasks.
     * @param ui User interface that user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showUnknownCommand(unknownCommand);
    }
}
