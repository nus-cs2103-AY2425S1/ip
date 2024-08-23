package matcha.command;

import matcha.exception.MatchaException;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

/**
 * Represents a command to be executed by the user.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Sets the isExit boolean value to true.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Returns the value of isExit.
     *
     * @return Boolean value of isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command given the task list, ui and storage objects.
     *
     * @param tasks Task list to execute command on.
     * @param ui Ui object to interact with user.
     * @param storage Storage object to save tasks to file.
     * @throws MatchaException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException;

}
