package loafy.command;

import loafy.task.Task;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui) {
        ui.reply(taskList.add(this.task));
    }
}
