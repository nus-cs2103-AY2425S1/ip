package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents a command to delete a task from the {@code TaskList}.
 * It expects an index of the task to be deleted, which is used to locate and remove the task from the list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a {@code DeleteCommand} with the given argument.
     *
     * @param argument the argument associated with this command. The argument should be a string representing
     *                 the index of the task to be deleted. The index is expected to be an integer value.
     */
    public DeleteCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to delete a task from the {@code TaskList}.
     * <p>
     * This method parses the command argument to obtain the task index, verifies the format and index validity,
     * removes the task from the {@code TaskList}, and displays a confirmation message using the {@code Ui} component.
     * If the argument is empty, incorrectly formatted, or if the index is invalid, an {@code InvalidCommandException}
     * is thrown.
     * </p>
     *
     * @param taskList the list of tasks from which the task will be removed.
     * @param ui the user interface component used to display the task removal confirmation to the user.
     *
     * @throws InvalidCommandException if the argument is empty, incorrectly formatted, or if the index is invalid
     *                                  (either not an integer or out of range).
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No task index provided. Please use the correct format: 'delete [task index]'"
            );
        }

        if (this.getArgument().split(" ").length != 1) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'delete [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(this.getArgument()) - 1;
            Task task = taskList.remove(index);
            ui.displayTaskRemoved(task, taskList.size());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }
}
