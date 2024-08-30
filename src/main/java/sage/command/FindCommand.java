package sage.command;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.task.Task;
import sage.ui.Ui;
import sage.storage.Storage;

import java.util.List;

/**
 * Represents a command to search for tasks by a keyword
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword
     *
     * @param keyword The keyword to search for in task descriptions
     */
    public FindCommand(String keyword) throws SageException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new SageException("The search keyword cannot be empty!");
        }
        this.keyword = keyword;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.searchTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found matching the keyword: " + keyword);
        }
        System.out.println("Here are the matching tasks in your list");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showLine();
            System.out.println(" " + (i + 1) + ". " + matchingTasks.get(i));
            ui.showLine();
        }
    }
}
