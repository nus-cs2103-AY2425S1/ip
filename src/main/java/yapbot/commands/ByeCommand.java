package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

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
