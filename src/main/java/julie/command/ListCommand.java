package julie.command;
import julie.command.Command;
import julie.misc.UI;
import julie.task.Task;

import java.util.List;

public class ListCommand extends Command {
    public ListCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void run(List<Task> taskList) {
        UI.printList(taskList);
    }
}
