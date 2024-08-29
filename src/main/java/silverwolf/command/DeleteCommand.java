package silverwolf.command;

import silverwolf.exception.SilverWolfException;
import silverwolf.storage.Storage;
import silverwolf.task.Task;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a task from the task list based on its index.
 * It extends the abstract Command class and implements the logic for task deletion.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified arguments.
     * Parses the arguments to determine the index of the task to be deleted.
     * Throws a SilverWolfException if the provided arguments are not a valid integer.
     *
     * @param arguments The arguments provided to the command, expected to be a string representation of the task index.
     * @throws SilverWolfException If the arguments are not a valid integer or if an invalid task number is provided.
     */
    public DeleteCommand(String arguments) throws SilverWolfException {
        try {
            this.index = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SilverWolfException("Invalid task number for deletion");
        }
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * Saves the updated task list to storage and displays confirmation messages to the user.
     *
     * @param tasks The TaskList object representing the list of tasks.
     * @param ui The Ui object used for interacting with the user.
     * @param storage The Storage object used for saving the updated task list.
     * @throws SilverWolfException If an error occurs during task deletion or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SilverWolfException {
        Task task = tasks.getTasks().get(index); // Retrieve the task to be deleted based on the index.
        tasks.deleteTask(index); // Remove the task from the task list.
        storage.save(tasks.getTasks()); // Save the updated task list to storage.
        ui.showLine(); // Display confirmation messages to the user.
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        ui.showLine();
    }
}
