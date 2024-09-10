package command;

import fridayException.FridayException;
import fridayException.InvalidUnmarkArgument;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be unmarked as not completed.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks the task as not completed, displays a message to the user on the CLI, and saves the task list to file.
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
                throw new InvalidUnmarkArgument();
            }
            Task taskToUnmark = tasks.getTask(taskIndex);  // Get the task to be unmarked
            tasks.unmarkTask(taskIndex);  // Unmark the task as not completed
            ui.showTaskUnmarked(taskToUnmark);  // Show the user that the task was unmarked
            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }

    /**
     * Unmarks the task as not completed, displays a message to the user on the GUI, and saves the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The result of the command execution.
     * @throws FridayException If an error occurs during execution of the command.
     */
    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) throws FridayException {
        Task taskToUnmark = tasks.getTask(taskIndex);  // Get the task to be unmarked
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidUnmarkArgument();
            }
            tasks.unmarkTask(taskIndex);  // Unmark the task as not completed
            gui.showTaskUnmarked(taskToUnmark);

            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
        return  gui.showTaskUnmarked(taskToUnmark);  // Show the user that the task was unmarked
    }
}