package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

public class TerminatingCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        ui.sayFarewell();
        storage.storeTasksToMemory(taskList);
    }
}
