package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.task.Task;

/**
 * The command that unmarks the task.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String commandString) {
        super(commandString);
    }
    @Override
    public String run(List<Task> taskList, Storage storage) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        t.unmarkCompleted();
        return String.format("Oop, this task is not yet done\n%s", t);
    }
}
