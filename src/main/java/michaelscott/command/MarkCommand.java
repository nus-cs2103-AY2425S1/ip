package michaelscott.command;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to mark a task as done.
 * This command implements the Command interface.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a new MarkCommand with the given task index.
     *
     * @param args A string representation of the task number to be marked as done.
     * @throws MichaelScottException If the provided argument cannot be parsed as a valid task number.
     */
    public MarkCommand(String args) throws MichaelScottException {
        assert args != null : "args cannot be null";

        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("If you tell me this is a number, you are fired.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        assert tasks != null : "tasks cannot be null";

        Task task = tasks.getTask(this.taskIndex);
        task.completeTask();
        return "Nice! I've marked this task as done:\n" + task.toString()
                + "\n now go back to work and stop eating the company's time";
    }

    @Override
    public String getSimpleName() {
        return "MarkCommand";
    }
}
