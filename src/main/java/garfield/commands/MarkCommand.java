package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The MarkCommand class represents a command to mark a specific task as completed
 * in the Garfield chatbot application.
 */
public class MarkCommand extends Command {

    private int taskId;

    /**
     * Constructs a new MarkCommand with the specified task ID.
     *
     * @param taskId The ID of the task to be marked as completed.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the MarkCommand by marking the specified task as completed, displaying
     * a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param tasks The TaskList in which the task will be marked as completed.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Nice. You actually did something. I've marked that task as done:\n\n\t"
                + tasks.mark(taskId));
        storage.save(tasks);

    }

    /**
     * Executes the MarkCommand by marking the specified task as completed, displaying
     * a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param tasks The TaskList in which the task will be marked as completed.
     * @param storage The Storage object used to save the updated task list.
     * @return A String of the response message by the chatbot to the user.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws GarfieldException {
        String result = tasks.mark(taskId);
        storage.save(tasks);
        return "Nice. You actually did something. I've marked that task as done:\n\n\t"
                + result;
    }
}
