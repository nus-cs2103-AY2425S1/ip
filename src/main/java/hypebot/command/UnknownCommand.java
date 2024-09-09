package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class UnknownCommand extends Command {
    private final String unknownCommand;

    public UnknownCommand(String command) {
        super();
        unknownCommand = command;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showUnknownCommand(unknownCommand);
    }
}
