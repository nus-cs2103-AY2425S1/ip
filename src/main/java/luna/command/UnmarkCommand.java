package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;

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

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException{
        return ">>> undo 'unmark' command\n"
                + tasks.markTaskAsDone(taskToUnmark, storage);
    }
}
