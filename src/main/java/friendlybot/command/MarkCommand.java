package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

/**
 * MarkCommand is a Command that can mark / unmark tasks as completed upon execution.
 */
public class MarkCommand extends Command {
    private boolean mark;
    private int taskNumber;

    /**
     * A constructor for MarkCommand.
     *
     * @param mark True if user wishes to mark the task as completed, False otherwise.
     * @param taskNumber The task number of the task to be marked / unmarked, which is one more than its index position.
     */
    public MarkCommand(boolean mark, int taskNumber) {
        this.mark = mark;
        this.taskNumber = taskNumber;
    }

    /**
     * Marks / Unmarks the Task upon execution.
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
            Task task = tasks.getTask(taskNumber);
            if (mark) {
                task.markAsDone();
                Ui.print("Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                Ui.print("OK, I've marked this task as not done yet:");
            }
            Ui.print("  " + task.toString());
        }
    }
}
