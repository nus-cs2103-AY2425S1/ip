package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;

/**
 * Represents a command to mark a task as completed in the {@code TaskList}.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a {@code MarkCommand} with the given argument.
     *
     * @param argument the argument associated with this command.
     */
    public MarkCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @param taskList The {@code TaskList} containing the task to be marked as completed.
     * @return A formatted string confirming that the task has been marked as done.
     * @throws InvalidCommandException If the argument is empty, incorrectly formatted, or if the index is invalid.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        try {
            checkArgument();
            int index = Integer.parseInt(this.getArgument()) - 1;
            Task task = taskList.mark(index);
            return String.format("Nice! I've marked this task as done:\n  %s\n", task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }


    /**
     * Validates the task index argument provided by the user.
     *
     * @throws InvalidCommandException if no task index is provided or if the input format is invalid.
     */
    private void checkArgument() throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException("No task index provided.");
        }

        if (this.getArgument().split(" ").length != 1) {
            throw new InvalidCommandException("Invalid input format.");
        }
    }
}
