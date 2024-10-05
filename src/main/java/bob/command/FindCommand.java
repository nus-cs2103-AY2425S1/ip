package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.task.Task;

/**
 * Represents a command that finds tasks containing the specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Returns a FindCommand object.
     * @param keyword Keyword for finding tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command, finding tasks containing the specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList results = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                results.add(task);
            }
        }

        return results.size() == 0
                ? "You have no matching tasks!"
                : String.format("Here are the matching tasks in your list:\n" +
                "%s", results);
    }

}