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
     * Sets the user data for the command.
     *
     * @param tasks the task list to be set.
     */
    public static void setUserData(TaskList tasks) {
        taskList = tasks;
    }

    /**
     * Sets the task list for the command.
     *
     * @param taskList the task list to be set.
     */
    public void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }
}
