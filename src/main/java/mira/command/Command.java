package mira.command;

import mira.ExpenseList;
import mira.TaskList;

/**
 * Represents an abstract command that can be executed.
 * Subclasses should implement specific command behaviors by overriding the {@code execute} method.
 */
public abstract class Command {
    /* TaskList to access lists of tasks */
    protected TaskList taskList;
    protected ExpenseList expenseList;

    /**
     * Sets the {@code TaskList} that this command will operate on.
     *
     * @param taskList The task list to be used by this command.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Sets the task list and expense list for the command.
     *
     * @param taskList The task list to set.
     * @param expenseList The expense list to set.
     */
    public void setTaskList(TaskList taskList, ExpenseList expenseList) {
        this.taskList = taskList;
        this.expenseList = expenseList;
    }

    /**
     * Executes the command.
     *
     * @return A message indicating the result of the command execution.
     */
    public abstract String execute();
}
