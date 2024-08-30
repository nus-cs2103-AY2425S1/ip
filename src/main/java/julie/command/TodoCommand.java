package julie.command;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;
import julie.task.ToDo;

import java.util.List;

public class TodoCommand extends Command {
    public TodoCommand(String commandString) {
        super(commandString);
    }
    @Override
    public void run(List<Task> taskList) {
        Task t = new ToDo(commandString.substring(5));
        taskList.add(t);
        UI.addedPrompt(t, taskList);
        Storage.save(t);
    }
}
