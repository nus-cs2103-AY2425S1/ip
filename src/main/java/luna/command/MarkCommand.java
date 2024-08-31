package luna.command;

import java.util.ArrayList;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

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
}

