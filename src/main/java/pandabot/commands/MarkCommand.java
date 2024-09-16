package pandabot.commands;

import java.io.IOException;

import pandabot.exceptions.InputException;
import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * This command handles the marking of a task at a specified index as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * The index is derived from the user input and validated to ensure it is a valid number.
     *
     * @param taskIndex the index of the task to be marked as done, as provided by the user.
     * @throws InputException if the task index is invalid, missing, or cannot be parsed into an integer.
     */
    public MarkCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to mark.");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim()) - 1;
            assert this.taskIndex >= 0 : "Task index should be a non-negative number";
        } catch (NumberFormatException e) {
            throw new InputException("Invalid task number format. Please enter a valid number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        assert !tasks.isEmpty() : "Task list should not be empty when attempting to mark";
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            storage.saveTaskList(tasks.getTasks());

            return "Nice! I've marked this task as done:\n"
                    + (tasks.get(taskIndex).toString());
        } else {
            return "The specified task does not exist.";
        }
    }
}

