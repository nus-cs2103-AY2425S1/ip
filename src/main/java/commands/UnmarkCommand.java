package commands;

import main.TaskList;
import ui.Ui;
import tasks.Task;
import storage.Storage;

/**
 * Represents a command to unmark a task as not completed in the task list.
 * This command updates the status of the specified task to not done and saves the changes.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task number.
     *
     * @param taskNumber the number of the task to be unmarked as not done (1-based index)
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command by marking the specified task as not done in the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks where the task is to be unmarked as not done
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task unmarked = taskList.get(this.taskNumber - 1);
            unmarked.markAsUndone();

            ui.showMessage("OK, I've marked this task as not done yet:\n" + unmarked.toString());
            storage.saveTasks(taskList.getTasks());
        }
    }
}