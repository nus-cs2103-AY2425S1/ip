package yapbot.commands;

import java.io.IOException;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for saving tasks to current file.
 */
public class SaveCommand extends Command {

    /**
     * {@inheritDoc}
     * Saves tasks to current file.
     *
     * @throws YapBotException If tasks cannot be saved to the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        String message = "Saving Tasks...";

        try {
            storage.save(tasks.saveTasks());

            return message + "Success\nYour tasks have been saved.";
        } catch (IOException e) {
            throw new YapBotException(message + "Failure\nFailed to save tasks.");
        }

    }
}
