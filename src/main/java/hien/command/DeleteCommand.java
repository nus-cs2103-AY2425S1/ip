package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Task;
import hien.ui.UI;

/**
 * Represents a command to delete a task or all tasks from the task list.
 * The command can either delete a specific task based on the provided index
 * or delete all tasks if specified.
 */
public class DeleteCommand extends Command {
    private String input;
    private boolean isDeleteAll = false;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param input       The user input specifying which task to delete.
     * @param isExit      Specifies whether this command will terminate the application.
     * @param isDeleteAll Specifies whether all tasks should be deleted.
     */
    public DeleteCommand(String input, boolean isExit, boolean isDeleteAll) {
        super(isExit);
        this.input = input;
        this.isDeleteAll = isDeleteAll;
    }

    /**
     * Checks if the provided index is a valid task index in the task list.
     *
     * @param tasks The task list.
     * @param index The index to be validated.
     * @return      {@code true} if the index is valid.
     * @throws HienException If the index is not a valid integer, is less than 1, or exceeds the task list size.
     */
    private boolean isValidIndex(TaskList tasks, String index) throws HienException {
        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new HienException("☹ OOPS!!! The index of the task is either empty or not an integer. Please try again!");
        }
        int i = Integer.parseInt(index);
        if (i < 1) {
            throw new HienException("☹ OOPS!!! Task index cannot be less than 1");
        } else if (i > tasks.size()) {
            throw new HienException("☹ OOPS!!! Task index cannot be greater than current number of tasks. "
                    + "You currently only have " + tasks.size() + " tasks.");
        } else {
            return true;
        }
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param input   The user input specifying the task index.
     * @param storage The storage object to save the updated task list.
     * @param ui      The UI object to interact with the user.
     * @return        A message indicating the result of the task deletion.
     * @throws HienException If the task index is invalid.
     */
    private String deleteTask(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String index = input.substring(6).trim();
        String msg = "";
        if (isValidIndex(tasks, index)) {
            int i = Integer.parseInt(index);
            assert i >= 1 && i <= tasks.size();
            Task removedTask = tasks.getTask(i - 1);
            tasks.deleteTask(i - 1);
            storage.save(tasks);
            msg += ui.showMessage(" Got it. I've deleted this task:");
            msg += ui.showMessage("   " + removedTask);
            msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        }
        return msg;
    }

    /**
     * Deletes all tasks from the task list.
     *
     * @param tasks   The task list from which all tasks will be deleted.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to save the updated task list.
     * @return        A message indicating that all tasks have been deleted.
     * @throws HienException If there is an issue saving the task list.
     */
    private String deleteAllTasks(TaskList tasks, UI ui, Storage storage) throws HienException {
        tasks.clear();
        storage.save(tasks);
        return ui.showMessage(" Got it. I've deleted all the tasks");
    }

    /**
     * Executes the delete command.
     * If {@code isDeleteAll} is true, it will delete all tasks; otherwise, it will delete a specific task.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object to save tasks.
     * @return        The result of the command execution as a string.
     * @throws HienException If the input index is invalid or deletion fails.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        if (isDeleteAll) {
            return deleteAllTasks(tasks, ui, storage);
        } else {
            return deleteTask(tasks, input, storage, ui);
        }
    }
}
