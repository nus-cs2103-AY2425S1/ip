package thebotfather.command;

import thebotfather.task.Task;
import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted from the task list.
     */
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param stringIndex The string representation of the task index to be deleted.
     * @throws TheBotFatherException If the string cannot be parsed into a valid integer.
     */
    public DeleteCommand(String stringIndex) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez\n" +
                    "\tTo delete a task enter \"delete <index>\"");
        }
    }

    /**
     * Executes the delete task command by removing the task from the task list, saving the updated task list to storage,
     * and printing the deleted task information.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui The user interface used to interact with the user.
     * @param storage The storage system used to save the task list.
     * @throws TheBotFatherException If an error occurs while saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        Task deletedTask = taskList.delete(index);
        storage.toFile(taskList);
        ui.printDeleted(deletedTask);
    }
}
