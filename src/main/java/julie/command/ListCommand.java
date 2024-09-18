package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;

/**
 * The command that returns the current List stored in the Chat bot.
 */
public class ListCommand extends Command {
    /**
     * Public constructor for a ListCommand
     * @param commandString The string to be processed.
     */
    public ListCommand(String commandString) {
        super(commandString);
    }

    @Override
    public String run(List<Task> taskList, Storage storage) {
        return UI.getListString(taskList);
    }
}
