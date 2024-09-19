package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.TaskList;

/**
 * Represents an executable command.
 *
 * The Command class is the parent class for all command types.
 * Has methods to set the task list, execute the specific command and a method to determine if it is an exit command.
 */
public class Command {
    protected TaskList taskList;

    /**
     * Constructs a Command with no initial data.
     * This constructor is typically used by subclasses that define specific command behaviors.
     */
    public Command() {}

    /**
     * Sets the TaskList that the command will operate on.
     *
     * @param taskList the TaskList to be used by the command
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns the result.
     * Subclasses should override this method to provide specific command functionality.
     *
     * @return the result of the command execution
     * @throws SnowyException if the command cannot be executed
     */
    public CommandResult execute() throws SnowyException {
        throw new SnowyException("No execution");
    }

    /**
     * Indicates whether the command should cause the application to exit.
     * By default, commands do not exit the application.
     *
     * @return false by default, true if the command should exit the application
     */
    public boolean isExit() {
        return false;
    }
}
