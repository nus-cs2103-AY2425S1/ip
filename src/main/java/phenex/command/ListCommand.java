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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        ui.printTaskList(taskList);
    }
}
