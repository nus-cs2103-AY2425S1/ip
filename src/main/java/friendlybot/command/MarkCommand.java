package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;
import friendlybot.task.Task;

public class MarkCommand extends Command {
    private boolean mark;
    private int taskNumber;

    public MarkCommand(boolean mark, int taskNumber) {
        this.mark = mark;
        this.taskNumber = taskNumber;
    }

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
