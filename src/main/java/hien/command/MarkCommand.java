package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

/**
 * Represents a command to mark a task as done or not done in the task list.
 * When executed, it marks the specified task as either completed or incomplete.
 */
public class MarkCommand extends Command {
    private String input;
    private boolean isDone;
    private UI ui;

    /**
     * Constructs a MarkCommand object.
     *
     * @param input  The input string containing the task index to be marked.
     * @param isDone Specifies whether the task is to be marked as done (true) or not done (false).
     * @param isExit Specifies whether this command will terminate the application.
     */
    public MarkCommand(String input, boolean isDone, boolean isExit) {
        super(isExit);
        this.input = input;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark a task as done or not done.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object used to save the updated task list.
     * @return        A string message indicating the result of marking the task.
     * @throws HienException If the task index is invalid or out of bounds.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return this.markTask(tasks, input, isDone, storage, ui);
    }

    /**
     * Marks the task at the specified index as done or not done.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param input   The input string containing the task index.
     * @param isDone  Specifies whether the task should be marked as done (true) or not done (false).
     * @param storage The storage object used to save the updated task list.
     * @param ui      The UI object used to interact with the user.
     * @return        A string message indicating the result of marking the task.
     * @throws HienException If the task index is invalid or out of bounds.
     */
    private String markTask(TaskList tasks, String input, boolean isDone, Storage storage, UI ui) throws HienException {
        String index = isDone ? input.substring(4).trim() : input.substring(6).trim();
        boolean isValidIndex = isValidIndex(index, tasks);
        String msg = "";
        if (isValidIndex) {
            int i = Integer.parseInt(index);
            assert i >= 1 && i <= tasks.size();
            if (isDone) {
                msg += ui.showMessage(" Nice! I've marked this task as done:");
            } else {
                msg += ui.showMessage(" OK, I've marked this task as not done yet:");
            }
            tasks.markTask(i - 1, isDone);
            msg += ui.showMessage("   " + tasks.getTask(i - 1));
            storage.save(tasks);
        }
        return msg;
    }

    /**
     * Validates whether the provided task index is valid.
     *
     * @param index The index of the task to be marked.
     * @param tasks The task list used for validation.
     * @return      true if the index is valid, otherwise throws a HienException.
     * @throws HienException If the index is empty, not an integer, or out of bounds.
     */
    private boolean isValidIndex(String index, TaskList tasks) throws HienException {
        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new HienException("☹ OOPS!!! The index of the task is either empty or not an integer. Please try again!");
        }
        int i = Integer.parseInt(index);
        if (i < 1) {
            throw new HienException("☹ OOPS!!! Task index cannot be less than 1");
        } else if (i > tasks.size()) {
            throw new HienException("☹ OOPS!!! Task index cannot be greater than the current number of tasks. "
                    + "You currently only have " + tasks.size() + " tasks.");
        } else {
            return true;
        }
    }
}
