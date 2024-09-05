package loafy.command;

import loafy.loafyexception.LoafyException;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList tasks, Ui ui) {
        if (tasks.isValid(this.taskId)) {
            ui.reply(tasks.delete(this.taskId));
        } else {
            ui.showError(LoafyException.ofInvalidAction());
        }
    }
}
