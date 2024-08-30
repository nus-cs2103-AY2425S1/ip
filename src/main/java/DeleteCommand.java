public class DeleteCommand extends Command {
    private final int taskId;

    DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    void execute(TaskList taskList, Ui ui) {
        if (taskList.isValid(this.taskId)) {
            ui.reply(taskList.delete(this.taskId));
        } else {
            ui.errorMsg(LoafyException.invalidAction());
        }
    }
}
