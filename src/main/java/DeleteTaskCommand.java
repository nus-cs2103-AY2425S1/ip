public class DeleteTaskCommand extends Command{
    private final int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        Task removed = tasks.removeTask(taskNum);
        storage.rewriteEntireFile(tasks.getList());
        ui.printToInterface("Noted. I've removed this task:");
        ui.printToInterface(removed.toString());
        ui.printToInterface(String.format("Now you have %d tasks in the list", tasks.getNumOfTasks()));
    }
}
