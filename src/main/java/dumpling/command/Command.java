package dumpling.command;

import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * Abstract class Command for other Command classes to inherit from
 */
public abstract class Command {

    /**
     * Function that executes the Command's intended action.
     *
     * @param taskList TaskList holding the tasks
     * @param ui Ui object to print messages to command line
     * @param storage Storage object to save data after Add, Delete or Mark commands
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Function that executes the Command's intended actions and returns the log as a string
     *
     * @param taskList TaskList holding the tasks
     * @param storage Storage object to save data after Add, Delete or Mark commands
     * @return String that holds the information of the log from performing the command
     */
    public abstract String executeAndReturnLog(TaskList taskList, Storage storage);

    /**
     * Function to flag if this Command is an exit command
     *
     * @return true only if Command is ByeCommand, false otherwise
     */
    public abstract boolean isExit();
}
