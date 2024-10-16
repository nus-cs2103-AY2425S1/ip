package michaelscott.command;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;


/**
 * Represents a command to add a new DeleteCommand to the task list.
 * This command parses the user input to
 * create a new delete command with an index number.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a new DeadlineCommand by parsing the given arguments.
     *
     * @param args The commands arguments except and integer that
     *             corresponds to the position of the task to be deleted in the taskList
     * @throws MichaelScottException If index > length of taskList or smaller than 0.
     */
    public DeleteCommand(String args) throws MichaelScottException {
        assert args != null : "args to delete cannot be null";

        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MichaelScottException("Please provide a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        assert tasks != null : "tasks cannot be null";

        Task deletedTask = tasks.getTask(this.taskIndex);
        tasks.removeTask(this.taskIndex);
        return "Not finished? You miss 100% of the shots you don’t take. Or finish.\n" + deletedTask.toString()
                + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }

    @Override
    public String getSimpleName() {
        return "DeleteCommand";
    }
}
