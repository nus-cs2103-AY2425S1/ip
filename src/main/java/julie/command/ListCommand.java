package julie.command;
import julie.command.Command;
import julie.misc.UI;
import julie.task.Task;

import java.util.List;

/**
 * The command that returns the current List stored in the chat bot.
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
    public void run(List<Task> taskList) {
        UI.printList(taskList);
    }
}
