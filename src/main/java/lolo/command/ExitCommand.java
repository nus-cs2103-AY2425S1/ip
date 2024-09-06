package lolo.command;

import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command signals that the application should terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by returning a goodbye message.
     * This command does not modify the task list or storage.
     *
     * @param tasks   The list of tasks, which is not modified by this command.
     * @param storage The storage, which is not used by this command.
     * @return A string containing the goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon! :D";
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, as this command is meant to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

