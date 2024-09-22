package rizz.command;
import rizz.source.TaskList;

public class MarkCommand extends Command {
    private final int[] index;

    public MarkCommand(int... index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        return "Nice! I've marked these task as done:\n" + tasks.markTask(index);
    }
}
