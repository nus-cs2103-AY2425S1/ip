package command;

import fridayException.FridayException;
import fridayException.InvalidDeleteArgument;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task from the task list, displays a message to the user on the CLI, and saves the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The CLI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @throws FridayException If an error occurs during execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidDeleteArgument();
            }
            Task taskToDelete = tasks.getTask(taskIndex);  // Get the task to be deleted
            tasks.removeTask(taskIndex);  // Remove the task from the list
            ui.showTaskRemoved(taskToDelete, tasks.size());  // Show the user that the task was removed
            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }

    /**
     * Deletes the task from the task list, displays a message to the user on the GUI, and saves the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The result of the command execution.
     * @throws FridayException If an error occurs during execution of the command.
     */
    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) throws FridayException {
        Task taskToDelete = tasks.getTask(taskIndex); // Get the task to be deleted
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidDeleteArgument();
            }
            tasks.removeTask(taskIndex);  // Remove the task from the list
            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
        return gui.showTaskRemoved(taskToDelete, tasks.size());  // Show the user that the task was removed
    }
}
