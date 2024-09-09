package command;

import task.TaskList;
import util.Storage;

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 * It extends the Command class and provides the implementation for displaying the task list to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by printing the current list of tasks to the console.
     * This command does not modify the task list or storage.
     *
     * @param tasks   The TaskList to be displayed.
     * @param storage The Storage instance, which remains unchanged by this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
