import java.util.ArrayList;
import luna.task.Deadline;
import luna.task.Task;

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
