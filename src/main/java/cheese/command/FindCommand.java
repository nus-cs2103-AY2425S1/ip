package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

/**
 * Command to search through tasks
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand, requires keyword to find task
     * @param inputTokens String[]
     * @throws CheeseException Ensure correct input
     */
    public FindCommand(String[] inputTokens) throws CheeseException {
        if (inputTokens.length != 2) {
            throw new CheeseException("FindCommand require 'find ....'");
        }
        this.keyword = inputTokens[1];
    }

    /**
     * Searches through task list to find matching tasks
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = tasks.search(keyword);
        return ui.say(matchedTasks);
    }
}
