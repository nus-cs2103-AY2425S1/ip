package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * ListCommand class which encapsulates a Command which lists all Tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        return ui.printTaskList(taskList);
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }
}
