package commands;

import task.TaskList;

/**
 * Represents an abstract command that can be executed on a task list.
 * Subclasses of {@code Command} must implement the {@code execute} method to define the
 * specific behavior of the command.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list.
     * Subclasses must implement this method to specify what actions are taken when the command is executed.
     *
     * @param taskList The task list on which the command is to be executed.
     * @return A {@code String} containing the result or feedback of the command execution.
     */
    public abstract String execute(TaskList taskList);
}
