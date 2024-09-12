package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * Abstract class Command to encapsulate a Command by the user of Phenex.
 */
public abstract class Command {

    /**
     * Checks whether a Command is a terminating command.
     * @return whether a command is a terminating command.
     */
    public abstract boolean isTerminatingCommand();

    /**
     * Executes the command.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return string that represents the execution of the command
     * @throws PhenexException if execution fails
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException;
}
