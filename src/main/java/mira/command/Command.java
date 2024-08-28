package mira.command;

import mira.TaskList;

/**
 * Represents an abstract command that can be executed.
 * Subclasses should implement specific command behaviors by overriding the {@code execute} method.
 */
public abstract class Command {
    /* TaskList to access lists of tasks */
    protected TaskList taskList;

    /**
     * Sets the {@code mira.TaskList} that this command will operate on.
     *
     * @param taskList The task list to be used by this command.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command.
     *
     * @return A message indicating the result of the command execution.
     */
    public abstract String execute();
}
