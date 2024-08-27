public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NetherException {
        if (taskIndex > tasks.getSize() || taskIndex < 0) {
            throw new NetherException("invalid task number!");
        }

        Task removedTask = tasks.getTask(taskIndex - 1);
        tasks.deleteTask(taskIndex - 1);
        ui.printTaskDeleted(removedTask, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
