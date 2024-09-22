package rizz.command;
import rizz.source.TaskList;
import rizz.task.Task;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public String execute(TaskList tasks) {
        Task task = tasks.getTask(taskIndex);
        task.unmarkAsDone();
        return "Nice! I've unmarked this task:\n" + task.toString();
    }
}

