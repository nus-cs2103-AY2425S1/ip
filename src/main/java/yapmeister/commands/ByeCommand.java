package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.TaskList;

import java.io.IOException;

/**
 * Represents Bye user command that closes the app
 */
public class ByeCommand implements Command {

    /**
     * Stores UI and exits the app
     * @param tasks
     * @param storage
     * @param ui
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws IOException {
        storage.saveLoadedTasks(tasks);
        ui.exit();
    }

    @Override
    public Command parse(String input) {
        return this;
    }
}
