package julie.command;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;
import julie.task.ToDo;

import java.util.List;

/**
 * The command that handles the creation of a ToDo Task.
 */
public class TodoCommand extends Command {
    /**
     * Public constructor for a TodoCommand
     * @param commandString The string to be processed.
     */
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
