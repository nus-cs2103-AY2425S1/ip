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
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        return tasks.unmarkTask(taskToUnmark, storage);
    }
}
