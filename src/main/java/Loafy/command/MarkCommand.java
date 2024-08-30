package loafy.command;

import loafy.loafyexception.LoafyException;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class MarkCommand extends Command {
    private final boolean isDone;
    private final int taskId;

    public MarkCommand(boolean isDone, int taskId) {
        this.isDone = isDone;
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isValid(this.taskId)) {
            ui.reply(taskList.markTask(this.isDone, this.taskId));
        } else {
            ui.showError(LoafyException.ofInvalidAction());
        }
    }
}
