package julie.command;

import java.util.List;

import julie.exception.InvalidInputException;
import julie.exception.JulieException;
import julie.misc.Storage;
import julie.task.Task;

/**
 * The command that handles the tagging of a task.
 */
public class TagCommand extends Command {
    public TagCommand(String commandString) {
        super(commandString);
    }
    @Override
    public String run(List<Task> taskList, Storage storage) throws JulieException {
        String[] tokens = commandString.split(" ");
        if (tokens.length != 3) {
            throw new InvalidInputException("Tag");
        }
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        t.tag("#" + tokens[2]);
        return String.format("Ooh, this task has been tagged!\n%s", t);
    }
}
