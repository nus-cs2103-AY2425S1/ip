public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(taskNum);
        } catch (PikappiException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
