package stobberi.command;

import stobberi.stobberiException.StobberiException;
import stobberi.components.TaskList;

/**
 * Represents a command to find and filter tasks in a {@link TaskList} based on a keyword.
 */
public class FindCommand extends Command {
    /**
     * The list of tasks to be filtered.
     */
    private TaskList taskList;

    /**
     * The keyword used for filtering tasks.
     */
    private String word;

    /**
     * Constructs a new {@code FindCommand} with the specified {@link TaskList} and keyword.
     *
     * @param taskList The list of tasks to be filtered.
     * @param word     The keyword used for filtering tasks.
     */
    public FindCommand(TaskList taskList, String word) {
        this.taskList = taskList;
        this.word = word;
    }

    /**
     * Executes the command by filtering the {@link TaskList} based on the specified keyword.
     *
     * @throws StobberiException if an error occurs during filtering.
     */
    @Override
    public void execute() throws StobberiException {
        taskList.filterListByWord(word);
    }
}
