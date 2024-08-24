public class UnmarkCommand extends Command {
    private final int taskToUnmark;

    public UnmarkCommand(int taskToUnmark) {
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.unmarkTask(taskToUnmark);
        storage.saveTasks(tasks.getTasks());
    }
}
