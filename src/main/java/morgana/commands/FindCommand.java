package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Represents a command to find tasks that contain a given keyword in their description.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_EMPTY_KEYWORD = "Please enter a keyword to search for.";

    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        if (keyword.isEmpty()) {
            throw new MorganaException(MESSAGE_EMPTY_KEYWORD);
        }
        return tasks.find(keyword);
    }
}
