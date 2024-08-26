package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents a command to unmark a task as completed in the {@code TaskList}.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an {@code UnmarkCommand} with the given argument.
     *
     * @param argument the argument associated with this command, which is expected to be the index of the task to
     *                 unmark. The index should be provided in a format like 'unmark [task index]'.
     */
    public UnmarkCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to unmark a task in the {@code TaskList}.
     * <p>
     * This method parses the task index from the argument, unmarks the corresponding task in the {@code TaskList},
     * and displays a message indicating that the task has been unmarked. If the argument is empty, incorrectly
     * formatted, or if the index is invalid, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks from which the specified task will be unmarked.
     * @param ui the user interface component used to display the task unmarking status to the user.
     *
     * @throws InvalidCommandException if the argument is empty, incorrectly formatted, or if the index is invalid
     *         (either not an integer or out of range).
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No task index provided. Please use the correct format: 'unmark [task index]'"
            );
        }

        if (this.getArgument().split(" ").length != 1) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'unmark [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(this.getArgument()) - 1;
            Task task = taskList.unmark(index);
            ui.displayTaskUnmarked(task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }
}
