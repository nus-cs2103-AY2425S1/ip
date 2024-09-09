package command;

import task.TaskList;
import util.Storage;

/**
 * The ExitCommand class represents a command to exit the Schedulo application.
 * It extends the Command class and provides the implementation for terminating the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a farewell message to the user.
     * This command does not modify the task list or storage.
     *
     * @param tasks   The TaskList, which remains unchanged by this command.
     * @param storage The Storage instance, which remains unchanged by this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Indicates that this command is an exit command, which will terminate the application.
     *
     * @return True, as this command is intended to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
