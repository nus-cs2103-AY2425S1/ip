package Johnson.command;

import Johnson.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 * This class serves as a base for all specific command implementations.
 */
public abstract class Command {

    protected static TaskList taskList;

    /**
     * Executes the command.
     *
     * @return the msg string if the command was executed successfully, null otherwise.
     */
    public abstract String executeCommand();

    /**
     * Sets the task list for commands.
     *
     * @param tasks the task list to be set.
     */
    public static void setTaskList(TaskList tasks) {
        taskList = tasks;
        assert taskList != null : "Task list should not be null";
    }

}
