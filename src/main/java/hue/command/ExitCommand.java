package hue.command;

import hue.ui.Ui;
import hue.storage.Storage;
import hue.task.TaskList;
import javafx.application.Platform;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
