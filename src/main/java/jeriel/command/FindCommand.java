package jeriel.command;

import jeriel.task.Task;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.TaskList;
import jeriel.util.Ui;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        // Validate the keyword is not null or empty
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword should not be null or empty";
        this.keyword = keyword.toLowerCase().trim();  // Case-insensitive and trim the input
    }

    /**
     * Executes the find command, searching for tasks that match the keyword.
     *
     * @param tasks the task list to search from
     * @param ui the UI for output (not used in GUI mode)
     * @param storage the storage (not used in this command)
     * @return the result to be displayed in the GUI
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Find matching tasks
        List<Task> matchingTasks = tasks.getTasks().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(keyword))  // Case-insensitive matching
            .collect(Collectors.toList());

        // Check if no matches were found
        if (matchingTasks.isEmpty()) {
            return "No tasks match your search keyword.";
        }

        // Format and return the matching tasks
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(String.format(" %d. %s\n", i + 1, matchingTasks.get(i)));
        }

        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
