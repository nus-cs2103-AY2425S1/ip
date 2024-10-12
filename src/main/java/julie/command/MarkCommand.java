package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.task.Task;

/**
 * The class that encapsulates a Mark Command.
 */
public class MarkCommand extends Command {
    public MarkCommand(String commandString) {
        super(commandString);
    }
    @Override
    public String run(List<Task> taskList, Storage storage) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        t.markCompleted();
        return String.format("Ooh, this task is done!\n%s", t);
    }
}
