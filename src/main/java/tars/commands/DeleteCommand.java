package tars.commands;

import tars.tasks.Task;
import tars.tasks.TaskList;

/**
 * Represents a command to delete an existing task from a {@link TaskList}.
 *
 * <p>The {@code DeleteCommand} class provides the functionality to remove a task
 * from the {@link TaskList} based on user input. It extends the {@link Command} class
 * and overrides the {@link Command#execute(String, TaskList)} method to implement
 * the specific behavior for deleting a task.</p>
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command, removing a specified task from the provided {@link TaskList}.
     *
     * @param input The input string representing the identifier or description of the task to be deleted.
     * @param tasks The {@link TaskList} object from which the task will be deleted.
     * @return A {@link String} message confirming the deletion of the task and showing the remaining number of tasks.
     */
    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.deleteTask(input);
        return String.format("Wow you're freeing yourself up\n   %s\nYou now have %s tasks left", t, tasks.noOfTasks());
    }
}