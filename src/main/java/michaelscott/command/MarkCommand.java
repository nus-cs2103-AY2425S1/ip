package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.Task;
import michaelscott.task.TaskList;

/**
 * Represents a command to mark a task as done.
 * This command implements the Command interface.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    public MarkCommand(String args) throws MichaelScottException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("If you tell me this is a number, you are fired.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        Task task = tasks.getTask(this.taskIndex);
        task.completeTask();
        return "Nice! I've marked this task as done:\n" + task.toString()
                + "\n now go back to work and stop eating the company's time";
    }
}
