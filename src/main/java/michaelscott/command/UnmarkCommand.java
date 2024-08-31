package michaelscott.command;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a new UnmarkCommand with the given task index.
     *
     * @param args A string representation of the task number to be unmarked.
     * @throws MichaelScottException If the provided argument cannot be parsed as a valid task number.
     */
    public UnmarkCommand(String args) throws MichaelScottException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("If you tell me this is a number, you are fired.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        Task task = tasks.getTask(this.taskIndex);
        task.undoTask();
        return "OK, I've marked this task as not done yet: \n" + task.toString();
    }
}
