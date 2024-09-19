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
     * @param inputs Inputs from user to unmark task.
     */
    public UnmarkCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1) {
            throw new LunaException("Indicate the task number to unmark\n"
                    + "e.g. unmark 2");
        }

        int taskToUnmark;
        try {
            taskToUnmark = Integer.parseInt(inputs[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new LunaException("Invalid task reference. Use integers only.");
        }

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
