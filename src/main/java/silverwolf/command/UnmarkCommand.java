package silverwolf.command;

import silverwolf.exception.SilverWolfException;
import silverwolf.storage.Storage;
import silverwolf.task.Task;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 * The command takes an index of the task to be unmarked and updates the task status accordingly.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the given arguments.
     * The arguments should be an integer representing the task index to unmark.
     *
     * @param arguments The arguments for the command, expected to be a task index.
     * @throws SilverWolfException If the arguments cannot be parsed into a valid task index.
     */
    public UnmarkCommand(String arguments) throws SilverWolfException {
        try {
            // Extract the task index from the arguments
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SilverWolfException("Hey! The index you provided is not a number.");
        }
    }

    /**
     * Executes the command to unmark the specified task as not done.
     * Retrieves the task at the specified index, updates its status, and saves the updated task list.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The Ui object for displaying messages to the user.
     * @param storage The Storage object for saving the updated task list.
     * @throws SilverWolfException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SilverWolfException {
        try {
            // Retrieve the specific task from the task list
            Task specificTask = tasks.getTask(taskIndex);
            // Mark the task as not done
            specificTask.unmarkTask();

            // Provide feedback to the user
            ui.showUnmarkTask(specificTask); // Assuming `Ui` has a method to show unmarked task

            // Save the updated task list to storage
            storage.save(tasks.getTasks());

        } catch (IndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey! The index you provided is not correct.");
        }
    }
}
