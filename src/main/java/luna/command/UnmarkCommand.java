package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to unmark task as not completed.
 */
public class UnmarkCommand implements Command {
    private final int taskToUnmark;
    private final Command previousCommand;
    private Task unmarkedTask;

    /**
     * Creates a command to unmark a task.
     *
     * @param taskToUnmark Index of task to unmark as not completed
     */
    public UnmarkCommand(int taskToUnmark, Command previousCommand) {
        this.taskToUnmark = taskToUnmark;
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        unmarkedTask = tasks.unmarkTask(taskToUnmark, storage);
        return "OK, I've marked this task as not done yet:\n"
                + "  " + unmarkedTask;
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskToMark = tasks.getTasks().indexOf(unmarkedTask);
        return ">>> undo 'unmark' command\n"
                + "Nice! I've marked this task as done:\n"
                + tasks.markTaskAsDone(taskToMark, storage);
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
