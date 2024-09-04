package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui.Ui;
import dumpling.Storage;

public abstract class Command {

    /**
     * Function that executes the Command's intended action.
     *
     * @param taskList TaskList holding the tasks
     * @param ui Ui object to print messages to command line
     * @param storage Storage object to save data after Add, Delete or Mark commands
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract String executeAndReturnLog(TaskList taskList, Storage storage);

    /**
     * Function to flag if this Command is an exit command
     *
     * @return true only if Command is ByeCommand, false otherwise
     */
    public abstract boolean isExit();
}
