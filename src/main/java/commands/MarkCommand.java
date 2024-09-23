package commands;

import main.TaskList;
import ui.Ui;
import tasks.Task;
import storage.Storage;

/**
 * Represents a command to mark a task as completed in the task list.
 * This command updates the status of the specified task and saves the changes.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a {@code MarkCommand} with the specified task number.
     *
     * @param taskNumber the number of the task to be marked as done (1-based index)
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command by marking the specified task as done in the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks where the task is to be marked as done
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task marked = taskList.get(this.taskNumber - 1);
            marked.markAsDone();

            ui.showMessage("Nice! I've marked this task as done:\n" + marked.toString());
            storage.saveTasks(taskList.getTasks());
        }
    }
}