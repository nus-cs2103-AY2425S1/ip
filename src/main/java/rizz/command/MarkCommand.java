package rizz.command;
import rizz.source.TaskList;

public class MarkCommand extends Command {
    private final int[] taskIndex;

    public MarkCommand(int... taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {
        return "Nice! I've marked these task as done:\n" + tasks.markTask(taskIndex);
    }
}
