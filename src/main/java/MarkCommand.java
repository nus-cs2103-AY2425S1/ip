public class MarkCommand extends Command {
    private final boolean isDone;
    private final int taskId;

    MarkCommand(boolean isDone, int taskId) {
        this.isDone = isDone;
        this.taskId = taskId;
    }

    void execute(TaskList taskList, Ui ui) {
        if (taskList.isValid(this.taskId)) {
            ui.reply(taskList.markTask(this.isDone, this.taskId));
        } else {
            ui.errorMsg(LoafyException.ofInvalidAction());
        }
    }
}
