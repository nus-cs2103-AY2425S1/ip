package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The UnmarkCommand class represents a command to mark a specific task as incomplete
 * in the Garfield chatbot application.
 */
public class UnmarkCommand extends Command {

    private int taskId;

    /**
     * Constructs a new UnmarkCommand with the specified task ID.
     *
     * @param taskId The ID of the task to be marked as incomplete.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the UnmarkCommand by marking the specified task as incomplete, displaying
     * a confirmation message to the user, and saves the updated task list to storage.
     *
     * @param tasks The TaskList in which the task will be marked as incomplete.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Oh, having second thoughts? OK, I've marked that task as not done yet:\n\n\t"
                + tasks.unmark(taskId) + "\n\nClearly, you're still undecided.");
        storage.save(tasks);
    }

    /**
     * Executes the UnmarkCommand by marking the specified task as incomplete, returns
     * a confirmation message to be shown to the user, and saves the updated task list to storage.
     *
     * @param tasks The TaskList in which the task will be marked as incomplete.
     * @param storage The Storage object used to save the updated task list.
     * @return A String representing the result of the command execution to be shown to the user.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws GarfieldException {
        String result = tasks.unmark(taskId);
        storage.save(tasks);
        return "Oh, having second thoughts? OK, I've marked that task as not done yet:\n\n\t"
                + result + "\n\nClearly, you're still undecided.";
    }
}
