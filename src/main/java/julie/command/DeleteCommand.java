package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.task.Task;

/**
 * The command that handles the deletion of a specified liner.
 */
public class DeleteCommand extends Command {
    /**
     * Public constructor for a DeleteCommand.
     * @param commandString The string to be processed.
     */
    public DeleteCommand(String commandString) {
        super(commandString);
    }
    @Override
    public String run(List<Task> taskList, Storage storage) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        taskList.remove(x);
        return String.format("Okay, I'll remove this task from the list!\n    %s\n "
                + "You still have %d tasks left!!", t, taskList.size());
    }
}
