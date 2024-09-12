package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * TerminatingCommand class which encapsulates a Command that exits the Phenex application.
 */
public class TerminatingCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        storage.storeTasksToMemory(taskList);
        return ui.sayFarewell();
    }

    @Override
    public boolean isTerminatingCommand() {
        return true;
    }
}
