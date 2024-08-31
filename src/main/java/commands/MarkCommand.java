package commands;
import tasks.Task;
import tasks.TaskList;
/**
 * Represents a command to mark a task as complete or incomplete in a {@link TaskList}.
 *
 * <p>The {@code MarkCommand} class provides the functionality to change the status
 * of a task in the {@link TaskList} to either done or not done based on user input.
 * It extends the {@link Command} class and overrides the {@link Command#execute(String, TaskList)}
 * method to implement the specific behavior for marking a task.</p>
 */
public class MarkCommand extends Command {

    /**
     * Executes the mark command, changing the status of a specified task in the provided {@link TaskList}.
     *
     * @param input The input string representing the task number of the task
     *              and whether it should be marked as done or undone.
     * @param tasks The {@link TaskList} object containing the task to be marked.
     * @return A {@link String} message indicating the updated status of the task.
     */
    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.findTask(input);

        String message;

        if (input.contains("unmark")) {
            t.markUndone();
            message = "Task undone. No worries, I won't judge... much.\n";
        } else {
            t.markDone();
            message = "Task complete. If I had arms, I might give you a pat on the back.\n";
        }

        return message + t;
    }
}