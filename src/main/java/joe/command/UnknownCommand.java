package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * This class is used to present unknown commands.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        throw new JoeException("Unknown command");
    }
}
