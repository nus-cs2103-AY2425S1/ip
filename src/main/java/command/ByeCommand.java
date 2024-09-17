package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents the bye command which saves the list into the storage
 * and reply to the user with a goodbye.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            storage.saveTasksToFile(list);
            return ui.goodbye();
        } catch (IOException e) {
            return "Unable to save chat data to local disk!\n";
        }
    }
}
