package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.task.Deadline;

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
}
