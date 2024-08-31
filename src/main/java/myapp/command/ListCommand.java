package myapp.command;

import myapp.core.BingBongException;
import myapp.core.Storage;
import myapp.task.TaskList;

/**
 * Represents a command that lists all the tasks in the task list.
 * This command will display all tasks along with their indices.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command by listing all tasks in the task list.
     * If the task list is empty, an exception is thrown indicating that no tasks have been saved.
     *
     * @param tasks   The task list containing the tasks to be listed.
     * @param storage The storage system (not used in this command).
     * @return A string message listing all tasks in the task list.
     * @throws BingBongException If the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws BingBongException {
        if (tasks.isEmpty()) {
            throw new BingBongException("No tasks have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return list.toString();
        }
    }

    /**
     * Returns false since a ListCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
