package moody.commands;

import moody.exceptions.InvalidCommandException;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks that contain a specific keyword.
 * The search is case-insensitive, and all tasks whose description contains the keyword will be returned.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword to search for.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching through the task list for tasks that contain
     * the specified keyword in their descriptions. A list of matching tasks is then displayed
     * to the user.
     *
     * @param tasks The list of tasks to search.
     * @param ui The user interface for showing messages.
     * @param storage The storage (not used in this command).
     * @return A string representation of the matching tasks.
     * @throws InvalidCommandException If the keyword is empty or only contains whitespace.
     * @throws IOException If an I/O error occurs during any process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, IOException {
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        if (keyword.trim().isEmpty()) {
            throw new InvalidCommandException("Error: The search keyword cannot be empty.\n");
        }

        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.toArrayList()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");

        if (matchingTasks.isEmpty()) {
            ui.showError("No tasks found with keyword: " + keyword);
            ui.showErrorAsString("No tasks found with keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
                response.append(i + 1)
                        .append(". ")
                        .append(matchingTasks.get(i))
                        .append("\n");
            }
            ui.showLine();
            response.append(ui.showLineAsString());
        }
        return response.toString();
    }
}
