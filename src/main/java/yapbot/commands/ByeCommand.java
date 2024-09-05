package yapbot.commands;

import java.io.IOException;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * Saves current Tasks onto file and closes the Ui.
     * If saving fails, the Ui is still closed regardless.
     *
     * @throws YapBotException If Tasks could not be saved onto file.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException {
        try {
            storage.save(tasks.saveTasks());
            ui.showShutdownMessage();

            return true;
        } catch (IOException e) {
            throw new YapBotException("Shutting down...\nError, failed to save tasks.\nYapBot process terminated.");
        } finally {
            ui.close();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
