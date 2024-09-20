package kobe.command;

import kobe.task.TaskList;
import kobe.util.Storage;
import kobe.util.Ui;

/**
 * Represents a command to find tasks based on a keyword or tag.
 * The command can search tasks based on their description or tags.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword or tag.
     *
     * @param keyword The keyword or tag to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which searches for tasks that match the keyword or tag.
     * If the keyword starts with "#", the search will be for tasks with the specified tag.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for displaying the search results.
     * @param storage The Storage object responsible for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = keyword.startsWith("#")
                ? tasks.findTasksByTag(keyword.substring(1))
                : tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.setResponse("No tasks found matching \"" + keyword + "\".");
        } else {
            ui.setResponse("Here are the matching tasks in your list:\n" + matchingTasks.getAllTasksAsString());
        }
    }
}
