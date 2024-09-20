package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to delete task from list of tasks.
 */
public class DeleteCommand implements Command {
    private final int taskToDelete;
    private final Command previousCommand;
    private Task deletedTask;

    /**
     * Creates a command to delete task.
     *
     * @param inputs Inputs from user for delete command.
     */
    public DeleteCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1) {
            throw new LunaException("Indicate the task number to delete\n"
                    + "e.g. delete 2");
        }

        int taskToDelete;
        try {
            taskToDelete = Integer.parseInt(inputs[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new LunaException("Invalid task reference. Use integers only.");
        }

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
