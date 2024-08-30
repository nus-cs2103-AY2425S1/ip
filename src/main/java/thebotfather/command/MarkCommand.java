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
     * Constructs a MarkCommand object with the specified task index and marking status.
     *
     * @param stringIndex The index of the task to be marked or unmarked.
     * @param isDone Indicates whether the task should be marked as done or unmarked.
     * @throws TheBotFatherException If the provided index is not a valid number.
     */
    public MarkCommand(String stringIndex, boolean isDone) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez\n" +
                    "\tTo mark/unmark a task enter \"mark/unmark <index>\"");
        }
        this.isDone = isDone;
    }

    /**
     * Executes the mark/unmark command by updating the task's status in the task list
     * and saving the updated task list to storage.
     *
     * @param taskList The task list containing the task to be marked or unmarked.
     * @param ui The user interface used to print the task's updated status.
     * @param storage The storage system used to save the updated task list.
     * @throws TheBotFatherException If an error occurs while updating the task list or saving it to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        if (isDone) {
            taskList.markAsDone(index);
            ui.printMarked(taskList.getTaskDescAtIndex(index));
        } else {
            taskList.unmark(index);
            ui.printUnmarked(taskList.getTaskDescAtIndex(index));
        }
        storage.toFile(taskList);

    }
}
