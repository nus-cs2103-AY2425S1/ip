package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

/**
 * DeleteCommand is a Command that deletes a certain Task upon execution.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * A constructor for DeleteCommand.
     *
     * @param taskNumber The task number of the task to be deleted, which is 1 more than its index.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task that corresponds to the task number upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int numTasks = tasks.getNumTasks();
        if (this.taskNumber > numTasks) {
            Ui.print("There's no such task yet!");
        } else {
            Task task = tasks.deleteTask(this.taskNumber);
            numTasks--;
            Ui.print("Noted. I've removed this task:");
            Ui.print("  " + task.toString());
            if (numTasks == 1) {
                Ui.print("Now you have 1 task in the list.");
            } else if (numTasks == 0) {
                Ui.print("You have no more tasks!");
            } else {
                Ui.print("Now you have " + numTasks + " tasks in the list.");
            }
        }
    }
}
