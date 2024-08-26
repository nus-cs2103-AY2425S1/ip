package friday.command;

import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand with the specified input.
     *
     * @param inputs The input array containing the index of the task to be deleted.
     * @throws FridayException If the input is invalid.
     */
    public DeleteCommand(String[] inputs) {
        if (!inputs[1].chars().allMatch(Character::isDigit)) {
            throw new FridayException("\tInvalid input. Where would you like to " + inputs[0] + "?");
        }
        this.index = Integer.parseInt(inputs[1]);
    }

    /**
     * Executes the delete command, removing the task from the task list and saving the updated list.
     *
     * @param tasks   The task list to be modified by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        if (index > tasks.getSize() || index <= 0) {
            throw new FridayException("\tInvalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        }
        Task task = tasks.getTasks().get(index - 1);
        tasks.deleteTask(index - 1);
        ui.showTaskDeleted(task, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
