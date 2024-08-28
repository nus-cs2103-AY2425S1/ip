package michaelscott.command;

import michaelscott.exception.MichaelScottException;
import michaelscott.task.Task;
import michaelscott.task.TaskList;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String args) throws MichaelScottException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("Please provide a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        Task deletedTask = tasks.getTask(this.taskIndex);
        tasks.removeTask(this.taskIndex);
        return "Noted. I've removed this task:\n" + deletedTask.toString() +
                "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
