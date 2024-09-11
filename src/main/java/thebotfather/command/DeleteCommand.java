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
     * Constructs a {@code DeleteCommand} using the specified string representation of the task index.
     *
     * @param stringIndex The string representing the index of the task to be removed.
     * @throws TheBotFatherException If the string cannot be parsed into a valid integer.
     */
    public DeleteCommand(String stringIndex) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez\n"
                    + "\tTo delete a task enter \"delete <index>\"");
        }
    }

    /**
     * Executes the delete command by removing the task from the task list, saving the updated list to storage,
     * and returning information about the deleted task.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui The user interface used to communicate with the user.
     * @param storage The storage system to persist the changes to the task list.
     * @return A string representation of the deleted task.
     * @throws TheBotFatherException If an error occurs while saving the task list to storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        Task deletedTask = taskList.delete(index);
        storage.toFile(taskList);
        return "You are sure you wanted to do that right? Anyways... too late\n"
                + deletedTask.toString() + " is deleted";
    }
}
