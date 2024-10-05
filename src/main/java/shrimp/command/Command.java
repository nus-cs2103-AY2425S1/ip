package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command that can be executed.
 */
public interface Command {

    /**
     * Executes the command with the given task list and user interface.
     *
     * @param taskList The list of tasks to perform the command on.
     * @param ui       The user interface to interact with the user.
     */
    String run(TaskList taskList, Ui ui);
}
