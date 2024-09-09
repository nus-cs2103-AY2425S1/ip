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
     * An empty constructor for the DeleteCommand, used to display the command format.
     */
    public DeleteCommand() {};

    /**
     * Deletes a task that corresponds to the task number upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        int numTasks = tasks.getNumTasks();
        if (this.taskNumber > numTasks) {
            Ui.print("There's no such task yet!");
            return "There's no such task yet!";
        }
        Task task = tasks.deleteTask(this.taskNumber);
        numTasks--;
        assert numTasks >= 0 : "There should not be a negative number of tasks in your list!";
        Ui.print("Noted. I've removed this task:");
        sb.append("Noted. I've removed this task:\n");
        Ui.print("  " + task.toString());
        sb.append("  ").append(task.toString()).append("\n");
        if (numTasks == 1) {
            Ui.print("Now you have 1 task in the list.");
            sb.append("Now you have 1 task in the list.");
        } else if (numTasks == 0) {
            Ui.print("You have no more tasks!");
            sb.append("You have no more tasks!");
        } else {
            Ui.print("Now you have " + numTasks + " tasks in the list.");
            sb.append("Now you have ").append(numTasks).append(" tasks in the list.");
        }
        storage.writeToFile(tasks.formatTasksToSave());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "delete <task_number> - Deletes the task corresponding to the given task number.";
    }
}
