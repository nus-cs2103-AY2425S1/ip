public class MarkCommand extends Command {
    private final boolean mark;
    private final int taskId;

    MarkCommand(boolean mark, int taskId) {
        this.mark = mark;
        this.taskId = taskId;
    }

    void execute(TaskList taskList, Ui ui) {
        if (taskList.isValid(this.taskId)) {
            ui.reply(taskList.markTask(this.mark, this.taskId));
        } else {
            ui.errorMsg(LoafyException.invalidAction());
        }
    }
}
