package julie.command;
import julie.misc.UI;
import julie.task.Task;

import java.util.List;

public class DeleteCommand extends Command {
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
