package yapbot.commands;

import java.io.IOException;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * This command signals YapBot to carry out closing tasks and terminate afterwards.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * Saves current Tasks onto file and closes the Ui.
     * If saving fails, the Ui is still closed regardless.
     *
     * @throws YapBotException If Tasks could not be saved onto file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        try {
            storage.save(tasks.saveTasks());

            return "YapBot Shutting Down...";
        } catch (IOException e) {
            throw new YapBotException("Shutting down...\nError, failed to save tasks.\nYapBot process terminated.");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
