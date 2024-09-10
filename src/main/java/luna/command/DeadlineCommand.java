package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Deadline;
import luna.task.Task;

/**
 * Represents a command to add task with deadline to list of tasks.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Creates a command to add task with deadline to list.
     *
     * @param deadline Deadline of task.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(deadline, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskNumber = tasks.getTasks().indexOf(deadline);
        Task deleted = tasks.deleteTask(taskNumber, storage);
        return ">>> undo 'deadline' command\n"
                + "I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}
