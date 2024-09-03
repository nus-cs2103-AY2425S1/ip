package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }
}
