package king.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;
import king.task.Task;

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
