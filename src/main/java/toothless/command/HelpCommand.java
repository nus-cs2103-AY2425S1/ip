package toothless.command;

import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to print the help message.
 */
public class HelpCommand extends Command {
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws ToothlessExceptions {
        return ui.helpMessage();
    }
}
