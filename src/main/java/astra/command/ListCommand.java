package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        ui.display(tasks.toString());
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
