package julie.command;

import java.util.List;

import julie.exception.InvalidInputException;
import julie.exception.JulieException;
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
    public String run(List<Task> taskList, Storage storage) throws JulieException {
        if (commandString.length() == 4) {
            throw new InvalidInputException("Todo");
        }
        Task t = new ToDo(commandString.substring(5));
        taskList.add(t);
        storage.save(t);
        return UI.addedPrompt(t, taskList);
    }
}
