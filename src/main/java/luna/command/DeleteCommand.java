package luna.command;

import java.util.ArrayList;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to delete task from list of tasks.
 */
public class DeleteCommand extends Command {
    private final int taskToDelete;

    /**
     * Creates a command to delete task.
     *
     * @param taskToDelete Index of task to delete.
     */
    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws LunaException {
        ArrayList<Task> deleted = tasks.deleteTask(taskToDelete);
        storage.saveTasks(deleted);
    }
}
