package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to mark task as completed.
 */
public class MarkCommand extends Command {
    private final int taskToMark;

    /**
     * Creates a command to mark a task.
     *
     * @param taskToMark Index of task to mark as completed.
     */
    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        return tasks.markTaskAsDone(taskToMark, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        return ">>> undo 'mark' command"
                + tasks.unmarkTask(taskToMark, storage);
    }
}

