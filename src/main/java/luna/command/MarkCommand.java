package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to mark task as completed.
 */
public class MarkCommand implements Command {
    private final int taskToMark;
    private final Command previousCommand;
    private Task markedTask;

    /**
     * Creates a command to mark a task.
     *
     * @param inputs Inputs from user to mark task.
     */
    public MarkCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1) {
            throw new LunaException("Indicate the task number to mark as done\n"
                    + "e.g. mark 2");
        }

        int taskToMark;
        try {
            taskToMark = Integer.parseInt(inputs[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new LunaException("Invalid task reference. Use integers only.");
        }

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

