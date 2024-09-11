package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand extends Command {

    private final int index;
    private final boolean isDone;

    /**
     * Constructs a {@code MarkCommand} with the specified task index and marking status.
     *
     * @param stringIndex The index of the task to be marked or unmarked.
     * @param isDone {@code true} if the task should be marked as done, {@code false} if unmarked.
     * @throws TheBotFatherException If the provided index is not a valid number.
     */
    public MarkCommand(String stringIndex, boolean isDone) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez\n"
                    + "To mark/unmark a task enter \"mark/unmark <index>\"");
        }
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark or unmark the task, updates the task's status in the task list,
     * and saves the updated list to storage.
     *
     * @param taskList The task list containing the task to be marked or unmarked.
     * @param ui The user interface used to print the task's updated status.
     * @param storage The storage system used to save the updated task list.
     * @return A string representing the task's updated status.
     * @throws TheBotFatherException If an error occurs while updating the task list or saving to storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        if (isDone) {
            taskList.markAsDone(index);
            storage.toFile(taskList);
            return "It will be done\n"
                    + taskList.getTaskDescAtIndex(index);
        } else {
            taskList.unmark(index);
            storage.toFile(taskList);
            return "A man who doesn't spend time with his family can never be a real man.\n"
                    + taskList.getTaskDescAtIndex(index);
        }
    }
}
