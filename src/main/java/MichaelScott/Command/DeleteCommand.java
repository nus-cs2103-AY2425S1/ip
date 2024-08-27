package MichaelScott.Command;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.Task;
import MichaelScott.Task.TaskList;

public class DeleteCommand implements Command {
    private final int TaskIndex;

    public DeleteCommand(String args) throws MichaelScottException {
        try {
            this.TaskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("Please provide a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        Task DeletedTask = tasks.getTask(this.TaskIndex);
        tasks.removeTask(this.TaskIndex);
        return "Noted. I've removed this task:\n" + DeletedTask.toString() +
                "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
