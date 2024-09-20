package elon.command;

import elon.Storage;
import elon.task.TaskList;
import elon.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param list the TaskList that the command will operate on
     * @param ui the user interface to respond to the user
     * @param storage the storage to save or load task data
     * @return a string representing the result of executing the command
     * @throws Exception if any error occurs during execution
     */
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws Exception;
}
