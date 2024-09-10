package commands;

import main.TaskList;
import ui.Ui;
import tasks.Task;
import storage.Storage;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task identified by its position in the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a {@code DeleteCommand} with the specified task number.
     *
     * @param taskNumber the number of the task to be deleted (1-based index)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks to delete the task from
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task deleted = taskList.get(this.taskNumber - 1);
            taskList.deleteTask(this.taskNumber - 1);
            int numTasks = taskList.size();

            ui.showMessage("Noted. I've removed this task:\n" + "  " + deleted.toString() + "\n  " + "\nNow you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}