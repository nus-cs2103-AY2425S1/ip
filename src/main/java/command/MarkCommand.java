package command;

import fridayException.FridayException;
import fridayException.InvalidMarkArgument;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as completed.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task as completed, displays a message to the user, and saves the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @throws FridayException If an error occurs during execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidMarkArgument();
            }
            Task taskToMark = tasks.getTask(taskIndex); // Get the task to be marked
            tasks.markTask(taskIndex); // Mark the task as completed
            ui.showTaskMarked(taskToMark); // Show the user that the task was marked

            storage.save(tasks.getTasks()); // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }

    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) throws FridayException {
        Task taskToMark = tasks.getTask(taskIndex); // Get the task to be marked
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidMarkArgument();
            }
            tasks.markTask(taskIndex); // Mark the task as completed

            storage.save(tasks.getTasks()); // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
        return gui.showTaskMarked(taskToMark); // Show the user that the task was marked
    }
}
