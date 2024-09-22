package rizz.command;
import rizz.source.TaskList;

public class UnmarkCommand extends Command {
    private final int[] index;

    public UnmarkCommand(int... index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        return "Nice! I've unmarked these task:\n" + tasks.unmarkTask(index);
    }
}

