package silverwolf.command;

import silverwolf.exception.SilverWolfException;
import silverwolf.storage.Storage;
import silverwolf.task.Task;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The MarkCommand class represents a command to mark a task as completed in the task list.
 * It extends the abstract Command class and implements the logic to update the status of a task.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified arguments.
     * Extracts and parses the task index from the arguments to identify which task to mark as done.
     *
     * @param arguments The arguments provided with the command, expected to be the task index.
     * @throws SilverWolfException If the arguments cannot be parsed into a valid task index.
     */
    public MarkCommand(String arguments) throws SilverWolfException {
        try {
            // Extract the task index from the arguments
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SilverWolfException("Hey! The index you provided is not a number.");
        }
    }

    /**
     * Executes the mark command by marking the specified task as completed.
     * Retrieves the task from the task list using the task index, updates its status,
     * provides feedback to the user, and saves the updated task list to storage.
     *
     * @param tasks The TaskList object representing the list of tasks.
     * @param ui The Ui object used for interacting with the user.
     * @param storage The Storage object for saving the updated task list.
     * @throws SilverWolfException If the task index is out of bounds or if there are issues with task processing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SilverWolfException {
        try {
            // Retrieve the specific task from the task list
            Task specificTask = tasks.getTask(taskIndex);
            // Mark the task as not done
            specificTask.markAsDone();
            // Provide feedback to the user
            ui.showMarkTask(specificTask); // Assuming `Ui` has a method to show unmarked task
            // Save the updated task list to storage
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey! The index you provided is not correct.");
        }
    }
}
