package commands;

import java.io.IOException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final String taskInput;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param taskInput The input string containing the task number to unmark.
     */
    public UnmarkCommand(String taskInput) {
        this.taskInput = taskInput.substring(7).trim();
    }

    /**
     * Executes the command to unmark a task as done.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        try {
            int taskIndex = Integer.parseInt(taskInput) - 1; // Convert to 0-based index
            if (taskIndex < 0 || taskIndex >= storage.getTasks().size()) {
                return ui.outputMessage("Invalid task number.");
            } else {
                Task task = storage.getTask(taskIndex);
                task.markAsUndone();
                storage.saveTasks();
                return ui.outputMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
        } catch (NumberFormatException e) {
            return ui.outputMessage("Please enter a valid task number.");
        } catch (IOException e) {
            return ui.outputMessage("An error occurred while saving tasks.");
        }
    }
}
