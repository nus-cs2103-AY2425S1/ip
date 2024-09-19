package friday.command;

import java.io.IOException;

import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to update the details of an existing task.
 * This command allows users to modify task attributes such as
 * description, deadline, or event timing without the need to delete
 * and recreate the task.
 */
public class UpdateCommand extends Command {
    private final int index;
    private final String updateDetails;

    /**
     * Constructs an {@code UpdateCommand} with the given inputs.
     * The inputs should specify the task index and the new details for the task.
     *
     * @param inputs The input array containing the task number and new details.
     * @throws FridayException If the task number is not a valid integer.
     */
    public UpdateCommand(String[] inputs) throws FridayException {
        try {
            this.index = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new FridayException("Please provide a valid task number.");
        }
        this.updateDetails = String.join(" ", inputs)
                .substring(inputs[0].length() + inputs[1].length() + 2);
    }

    /**
     * Executes the update command, modifying the specified task with the
     * provided details. The updated task is saved to storage after modification.
     *
     * @param tasks   The {@code TaskList} containing all the tasks.
     * @param ui      The {@code Ui} instance used to display messages to the user.
     * @param storage The {@code Storage} instance used to save tasks to persistent storage.
     * @return A success message indicating the task has been updated.
     * @throws FridayException If the task cannot be found or updated due to invalid inputs.
     * @throws IOException     If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FridayException, IOException {
        Task taskToUpdate = tasks.getTask(index);

        taskToUpdate.update(updateDetails);

        storage.saveTasks(tasks.getTasks());
        return ui.showUpdateSuccess(taskToUpdate);
    }
}
