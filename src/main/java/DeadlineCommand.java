public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(deadline);
        storage.saveTasks(tasks.getTasks());
    }
}
