package nether.command;

import nether.NetherException;
import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * Represents a command to let Nether express its personality to the users.
 * The {@code NetherCommand} class returns a response String that lets the users know more about Nether's behaviour.
 *
 */
public class NetherCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NetherException {
        return ui.printSelf();
    }
}
