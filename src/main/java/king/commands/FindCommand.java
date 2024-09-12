package king.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.task.Task;
import king.ui.Ui;

/**
 * Represents a command to find tasks containing a keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase(); // Convert keyword to lowercase for case-insensitive search
    }

    /**
     * Executes the command to find tasks in the list that match the specified keyword.
     * <p>
     * This method searches through the list of tasks and returns a message based on whether
     * tasks containing the keyword are found. The search is case-insensitive.
     * </p>
     *
     * @param tasks the {@code TaskList} containing the list of tasks to be searched
     * @param ui the {@code Ui} instance used for displaying messages to the user
     * @param storage the {@code Storage} instance for saving or loading tasks; not used in this method
     * @return a {@code String} message indicating the result of the search:
     *         - If no tasks match the keyword, a message indicating no tasks were found is returned.
     *         - If matching tasks are found, a message displaying the tasks is returned.
     * @throws KingException if the keyword is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        if (this.keyword.isEmpty()) {
            throw new KingException("Search keyword cannot be empty!");
        }
        ArrayList<Task> matchingTasks = tasks.getTaskList().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        if (matchingTasks.isEmpty()) {
            return ui.showNoTaskFound(keyword);
        } else {
            return ui.showTasksFound(matchingTasks);
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
