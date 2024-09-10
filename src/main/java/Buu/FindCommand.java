package Buu;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks in the GPT application based on a keyword.
 * The command searches through the task list and displays matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand by extracting the keyword from the user input.
     *
     * @param input The user input string containing the command to find tasks.
     */
    public FindCommand(String input) {
        // Precondition: Ensure input is not null
        assert input != null : "Input should not be null";

        // Extract keyword after "find ", but check if input length is valid to avoid errors.
        if (input.trim().equals("find") || input.length() <= 5) {
            this.keyword = ""; // No keyword provided
        } else {
            this.keyword = input.substring(5).trim();
        }
        // Postcondition: Ensure keyword is properly initialized
        assert keyword != null : "Keyword should not be null";
    }

    /**
     * Executes the command to find tasks that match the keyword.
     * Displays the matching tasks or a message if no tasks are found.
     *
     * @param taskList The list of tasks to search.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Preconditions: Ensure taskList, ui, and storage are not null
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";
        // Check if the keyword is empty or invalid
        if (keyword.isEmpty()) {
            ui.showError(
                    "Invalid command format for find.\nUsage: find [keyword]\n"
                            + "Example: find project"
            );
            return;
        }

        // Proceed with the search if the keyword is valid
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }

        // Show results or message if no matching tasks are found
        if (matchedTasks.isEmpty()) {
            ui.showError("No tasks found matching the keyword: " + keyword);
        } else {
            ui.showMatchingTasks(matchedTasks);
        }
    }
}
