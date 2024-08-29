public class MarkCommand extends Command {
    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.showMessage("Nice. You actually did something. I've marked that task as done:\n\n\t"
                    + taskList.mark(taskId));
            storage.save(taskList);
        } catch (Exception e) {
            // todo: handle exceptions
        }
    }
}
