public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.deleteTask(taskToDelete);
        storage.saveTasks(tasks.getTasks());
    }
}
