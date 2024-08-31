package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

public abstract class Command {

    /** Executes the command
     *
     * @param taskList, the list of tasks
     * @param ui, the ui
     * @param storage, the storage
     * @throws PhenexException, if invalid inputs
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException;
}
