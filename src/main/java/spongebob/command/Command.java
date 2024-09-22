package spongebob.command;


import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.ui.Ui;


/**
 * Basic Command class for executing tasks
 */

public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if command is an exit command and returns a boolean value.
     * @return true if it is an ExitCommand, else false
     */
    public abstract boolean isExit();

    /**
     * Returns a list of strings corresponding to the
     * arguments given by the user, with the first item being the command
     * and second items onwards for items such as time, task etc.
     * @return List of strings for
     */
    public abstract String[] getArgs();
}
