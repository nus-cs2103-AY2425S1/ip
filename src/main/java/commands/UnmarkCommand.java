package commands;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

import java.io.IOException;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private String taskInput;

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
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     * @throws SkibidiException If the task number is in an invalid format.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) throws SkibidiException {
        try {
            int taskIndex = Integer.parseInt(taskInput) - 1; // Convert to 0-based index
            if (taskIndex < 0 || taskIndex >= storage.getTasks().size()) {
                ui.printMessage("Invalid task number.");
            } else {
                Task task = storage.getTask(taskIndex);
                task.markAsUndone();
                storage.saveTasks();
                ui.printMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
        } catch (NumberFormatException e) {
            throw new SkibidiException("Please enter a valid task number.");
        } catch (IOException e) {
            throw new SkibidiException("An error occurred while saving tasks.");
        }
        return true;
    }
}
