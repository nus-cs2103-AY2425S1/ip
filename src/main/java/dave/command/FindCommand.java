package dave.command;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks with a keyword in the task list.
 * The command searches the task descriptions for matches with the given keyword.
 */
public class FindCommand extends Command {

    /**
     * The keyword to search for in task descriptions.
     */
    private String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param description The keyword used to search for tasks in the task list.
     */
    public FindCommand(String description) {
        this.keyword = description;
    }

    /**
     * Executes the find command. It searches the tasks in the provided TaskList
     * for those that match the keyword and returns a formatted string of results.
     *
     * @param tasks The TaskList to search for matching tasks.
     * @param storage The Storage instance, not used in this command.
     * @param ui The Ui instance for user interactions, not used in this command.
     * @return A String of tasks that match the keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            List<Task> matchingTasks = tasks.findTasks(keyword);
            if (matchingTasks.isEmpty()) {
                return "No tasks found matching: " + keyword;
            }
            return matchingTasks.stream()
                    .map(Task::toString)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Unexpected error occurred: " + e.getMessage();
        }
    }
}

