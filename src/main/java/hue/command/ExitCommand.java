package hue.command;

import hue.ui.ui;
import hue.storage.Storage;
import hue.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
