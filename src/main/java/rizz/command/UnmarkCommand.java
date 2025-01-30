package rizz.command;
import rizz.source.TaskList;

public class UnmarkCommand extends SaveableCommand {
    private final int[] taskIndex;

    public UnmarkCommand(int... taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {
        return "Nice! I've unmarked these task:\n" + tasks.unmarkTask(taskIndex);
    }
}

