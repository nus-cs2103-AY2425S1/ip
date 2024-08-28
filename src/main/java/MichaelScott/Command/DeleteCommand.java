package MichaelScott.Command;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.Task;
import MichaelScott.Task.TaskList;


/**
 * Represents a command to add a new DeleteCommand to the task list.
 * This command parses the user input to
 * create a new delete command with an index number.
 */
public class DeleteCommand implements Command {
    private final int TaskIndex;

    /**
     * Constructs a new DeadlineCommand by parsing the given arguments.
     *
     * @param args The commands arguments except and integer that
     *             corresponds to the position of the task to be deleted in the taskList
     * @throws MichaelScottException If index > length of taskList or smaller than 0.
     */
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
