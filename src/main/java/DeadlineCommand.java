import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> added = tasks.addTask(deadline);
        storage.saveTasks(added);
    }
}
