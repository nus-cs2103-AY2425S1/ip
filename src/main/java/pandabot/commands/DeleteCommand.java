package pandabot.commands;

import java.io.IOException;

import pandabot.exceptions.InputException;
import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.tasks.Task;
import pandabot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command handles the removal of a task at a specified index in the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     * The index is derived from the user input and validated to ensure it is a valid number.
     *
     * @param taskIndex the index of the task to be deleted, as provided by the user.
     * @throws InputException if the task index is invalid, missing, or cannot be parsed into an integer.
     */
    public DeleteCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to delete.");
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
        assert !tasks.isEmpty() : "Task list should not be empty when attempting a delete";
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            storage.saveTaskList(tasks.getTasks());

            return "Noted. I've removed this task:\n"
                    + removedTask.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            return "The specified task does not exist.";
        }
    }
}
