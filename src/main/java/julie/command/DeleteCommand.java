package julie.command;
import julie.misc.UI;
import julie.task.Task;

import java.util.List;

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
    public void run(List<Task> taskList) {
        String[] tokens = commandString.split(" ");
        int x = Integer.parseInt(tokens[1]) - 1;
        Task t = taskList.get(x);
        taskList.remove(x);
        UI.wrappedLinePrint(String.format("Okay, I'll remove this task from the list!\n    %s\n You still have %d tasks left!!", t, taskList.size()));
    }
}
