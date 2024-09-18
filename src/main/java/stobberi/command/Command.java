package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command that can be executed. The command has an exit flag that indicates
 * whether the command should terminate the application or not.
 */
public abstract class Command {

    /**
     * The list of tasks to which the to-do task will be added.
     */
    private TaskList taskList;

    /**
     * The rest of the command given.
     */
    private String restOfCommand;
    /**
     * Indicates whether the command should exit the application.
     */
    private boolean isExit;

    /**
     * Constructs a new Command object with the exit flag set to false.
     */
    public Command(TaskList taskList, String restOfCommand) {
        this.isExit = false;
        this.taskList = taskList;
        this.restOfCommand = restOfCommand;
    }

    /**
     * Executes the command. This method should be overridden by subclasses to provide
     * specific command execution logic.
     *
     * @throws StobberiException if an error occurs during command execution
     */
    public String execute() throws StobberiException {
        return "";
    }

    /**
     * Sets the exit flag to true, indicating that the command should terminate the application.
     */
    public void setExitTrue() {
        this.isExit = true;
    }

    /**
     * Returns list of task.
     *
     * @return list of tasks
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns rest of the command.
     *
     * @return the rest of the commands
     */
    public String getRestOfCommand() {
        return this.restOfCommand;
    }

    /**
     * Returns whether the command should exit the application.
     *
     * @return true if the command should exit, false otherwise
     */
    public boolean isExit() {
        return isExit;
    }
}
