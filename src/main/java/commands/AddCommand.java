package commands;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a command to add a new task to a {@link TaskList}.
 *
 * <p>The {@code AddCommand} class encapsulates the logic for adding a task based
 * on user input. It extends the {@link Command} class and overrides the
 * {@link Command#execute(String, TaskList)} method to provide the specific
 * behavior for adding a task.</p>
 */
public class AddCommand extends Command {

    /**
     * Executes the add command, adding a new task to the provided {@link TaskList}.
     *
     * @param input The input string containing the description of the task to be added.
     * @param tasks The {@link TaskList} object to which the new task will be added.
     * @return A {@link String} message confirming that the task has been added, including
     *         the total number of tasks in the list.
     */
    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.addTask(input);

        return String.format("Added yet another task\n   %s\nYou now have %d tasks. Are you gonna do any of them?", t, tasks.noOfTasks());

    }
}
