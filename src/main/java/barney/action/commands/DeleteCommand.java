package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

/**
 * Represents a command to delete a task from the task list. Extends the
 * {@link Command} class.
 */
public class DeleteCommand extends Command {

    /**
     * Represents a delete command.
     * <p>
     * This command is used to delete an item based on the provided arguments.
     *
     * @param argumentMap A HashMap containing the arguments for the delete command.
     */
    public DeleteCommand(HashMap<String, String> argumentMap) {
        super("delete", argumentMap);
    }

    /**
     * Executes the delete command, which deletes a task from the task list.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui    The user interface to display the result of the delete
     *              operation.
     * @return {@code true} if the delete command is executed successfully,
     * {@code false} otherwise.
     * @throws InvalidArgumentException If the index of the task to delete is out of
     *                                  range.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String index = getParameter("index");
        int deleteIndex = Integer.parseInt(index) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new InvalidArgumentException("The index of a task to delete is out of range!");
        }

        Task deletedTask = tasks.pop(deleteIndex);
        ui.printDeleteTask(deletedTask, tasks.size());

        return true;
    }
}
