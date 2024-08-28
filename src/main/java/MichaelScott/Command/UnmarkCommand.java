package MichaelScott.Command;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.Task;
import MichaelScott.Task.TaskList;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand implements Command {
    private final int TaskIndex;

    public UnmarkCommand(String args) throws MichaelScottException {
        try {
            this.TaskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("Please enter a valid number");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        Task task  = tasks.getTask(this.TaskIndex);
        task.undoTask();
        return "OK, I've marked this task as not done yet: \n" + task.toString();
    }
}
