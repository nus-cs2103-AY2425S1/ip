public class MarkCommand extends Command{
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        Task task = tasks.getTask(taskNum);

        task.markAsComplete();
        storage.rewriteEntireFile(tasks.getList());
        ui.printToInterface("Nice! I've marked this task as complete:");
        ui.printToInterface(task.toString());
    }
}
