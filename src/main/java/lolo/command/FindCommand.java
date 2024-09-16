package lolo.command;

import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * A command to find tasks by a keyword in their description.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, returning tasks that match the keyword.
     *
     * @param tasks   The list of tasks to search.
     * @param storage The storage, which is not used in this command.
     * @return A string listing the tasks that match the keyword.
     * @throws LoloException If there is an issue during execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws LoloException {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(matchingTasks.get(i));
        }

        return result.toString();
    }
}

