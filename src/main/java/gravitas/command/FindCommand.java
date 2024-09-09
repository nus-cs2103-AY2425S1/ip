package gravitas.command;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {

    private static final String EMPTY_FIND = "OOPS!!! The keyword to find cannot be empty.";
    private String userInput;

    /**
     * Constructor for FindCommand.
     *
     * @param userInput User input is used to find tasks in the task list.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to find tasks in the task list.
     */
    @Override
    public String executeCommand(TaskList tasklist, Storage storage) throws DukeException {
        String[] msgFrag = userInput.split(" ", 2);

        if (msgFrag.length <= 1) {
            throw new DukeException(EMPTY_FIND);
        }
        String keyword = msgFrag[1];
        assert !keyword.isEmpty() : "Keyword should not be empty";
        return tasklist.printFindTask(keyword);
    }
}
