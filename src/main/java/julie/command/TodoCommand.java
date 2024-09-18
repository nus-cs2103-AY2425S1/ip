package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;
import julie.task.ToDo;

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
    public String run(List<Task> taskList, Storage storage) {
        Task t = new ToDo(commandString.substring(5));
        taskList.add(t);
        storage.save(t);
        return UI.addedPrompt(t, taskList);
    }
}
