package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;
import friendlybot.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
