package myapp.command;

import myapp.core.Storage;
import myapp.task.TaskList;

/**
 * Represents a command that exits the application.
 * This command terminates the application's interaction loop when executed.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the command by returning a farewell message.
     * This command does not modify the task list or storage, as it is intended
     * to terminate the application.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage system (not used in this command).
     * @return A string message indicating that the application is exiting.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns true, indicating that this command causes the application to exit.
     *
     * @return true, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
