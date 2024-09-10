package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to delete task from list of tasks.
 */
public class DeleteCommand extends Command {
    private final int taskToDelete;
    private final Command previousCommand;
    private Task deletedTask;

    /**
     * Creates a command to delete task.
     *
     * @param taskToDelete Index of task to delete.
     */
    public DeleteCommand(int taskToDelete, Command previousCommand) {
        this.taskToDelete = taskToDelete;
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        Task removed = tasks.deleteTask(taskToDelete, storage);
        deletedTask = removed;
        return "Noted, I've removed this task:\n"
                + "  " + removed + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    @Override
    public String undo(TaskList tasks, Storage storage) {
        return ">>> undo 'delete' command\n"
                + tasks.addTask(deletedTask, storage);
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
