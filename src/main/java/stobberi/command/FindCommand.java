package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to find and filter tasks in a {@link TaskList} based on a keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a new {@code FindCommand} with the specified {@link TaskList} and keyword.
     *
     * @param taskList      The list of tasks to be filtered.
     * @param restOfCommand The keyword used for filtering tasks.
     */
    public FindCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by filtering the {@link TaskList} based on the specified keyword.
     *
     * @return A string containing the filtered tasks that match the keyword.
     * @throws StobberiException If an error occurs during filtering.
     */
    @Override
    public String execute() throws StobberiException {
        String word = getRestOfCommand();
        return getTaskList().filterListByWord(word);
    }
}
