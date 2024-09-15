package popi.command;

import popi.exception.PopiException;
import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface to display information after executed.
     * @param taskManager The task manager that store and load the tasks.
     * @throws PopiException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, TaskManager taskManager) throws PopiException;

    public boolean isExit() {
        return false;
    }
}
