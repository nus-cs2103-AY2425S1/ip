package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;

/**
 * Represents a command to unmark a task as completed in the {@code TaskList}.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an {@code UnmarkCommand} with the given argument.
     *
     * @param argument the argument associated with this command.
     */
    public UnmarkCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to unmark a task in the {@code TaskList}.
     *
     * @param taskList The {@code TaskList} from which the specified task will be unmarked.
     * @return A formatted string confirming that the task has been marked as not done yet.
     * @throws InvalidCommandException If the argument is empty, incorrectly formatted, or if the index is invalid.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException("No task index provided.");
        }

        if (this.getArgument().split(" ").length != 1) {
            throw new InvalidCommandException("Invalid input format.");
        }

        try {
            int index = Integer.parseInt(this.getArgument()) - 1;
            Task task = taskList.unmark(index);
            return String.format("OK, I've marked this task as not done yet:\n  %s\n", task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }
}
