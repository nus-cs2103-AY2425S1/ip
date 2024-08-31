package luna.command;

import java.util.ArrayList;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to unmark task as not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskToUnmark;

    /**
     * Creates a command to unmark a task.
     *
     * @param taskToUnmark Index of task to unmark as not completed
     */
    public UnmarkCommand(int taskToUnmark) {
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws LunaException {
        ArrayList<Task> unmarked = tasks.unmarkTask(taskToUnmark);
        storage.saveTasks(unmarked);
    }
}
