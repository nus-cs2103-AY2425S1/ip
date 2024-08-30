package julie.command;

import julie.command.Command;
import julie.misc.UI;
import julie.task.Task;
import java.util.List;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String commandString) {
        super(commandString);
    }
    @Override
    public void run(List<Task> taskList) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        t.unmark();
        UI.wrappedLinePrint(String.format("Oop, this task is not yet done\n%s", t));
    }
}
