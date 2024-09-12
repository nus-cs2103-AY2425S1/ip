package commands;

import java.util.List;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand.
     *
     * @param input The input string containing the keyword to search for.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public FindCommand(String input) throws SkibidiException {
        this.keyword = extractKeyword(input);
    }

    /**
     * Extracts the keyword from the input string.
     *
     * @param input The input string containing the keyword.
     * @return The extracted keyword.
     * @throws SkibidiException If the keyword is empty.
     */
    private String extractKeyword(String input) throws SkibidiException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new SkibidiException("OOPS!!! The keyword cannot be empty.");
        }
        return keyword;
    }

    /**
     * Formats the list of tasks into a string.
     *
     * @param tasks The list of tasks to format.
     * @return The formatted string of tasks.
     */
    private String formatTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder formattedTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            formattedTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return formattedTasks.toString();
    }
    /**
     * Executes the command to find tasks by keyword.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        List<Task> matchingTasks = storage.findTasksByKeyword(keyword);
        return ui.outputMessage(formatTasks(matchingTasks));
    }
}
