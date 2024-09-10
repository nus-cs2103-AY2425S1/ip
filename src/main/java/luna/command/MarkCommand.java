package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to mark task as completed.
 */
public class MarkCommand extends Command {
    private final int taskToMark;
    private final Command previousCommand;
    private Task markedTask;

    /**
     * Creates a command to mark a task.
     *
     * @param taskToMark Index of task to mark as completed.
     */
    public MarkCommand(int taskToMark, Command previousCommand) {
        this.taskToMark = taskToMark;
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        markedTask = tasks.markTaskAsDone(taskToMark, storage);
        return "Nice! I've marked this task as done:\n"
                + "  " + markedTask;
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskToUnmark = tasks.getTasks().indexOf(markedTask);
        return ">>> undo 'mark' command\n"
                + "OK, I've marked this task as not done yet:\n"
                + tasks.unmarkTask(taskToUnmark, storage);
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}

