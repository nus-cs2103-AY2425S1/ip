package julie.command;
import julie.misc.UI;
import julie.task.Task;
import java.util.List;

public class MarkCommand extends Command {
    public MarkCommand(String commandString) {
        super(commandString);
    }
    @Override
    public void run(List<Task> taskList) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        t.markCompleted();
        UI.wrappedLinePrint(String.format("Ooh, this task is done!\n%s", t));
    }
}
