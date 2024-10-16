package michaelscott.command;

import java.util.ArrayList;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to find tasks containing a specific keyword.
 * This class searches for tasks in the task list that contain the given keyword
 * in their description and returns a list of matching tasks.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @throws MichaelScottException If the keyword is empty or consists only of whitespace.
     */
    public FindCommand(String keyword) throws MichaelScottException {
        assert keyword != null : "keyword cannot be null";

        if (keyword.trim().isEmpty()) {
            throw new MichaelScottException(
                    "You need to tell me what to search for! [find <Stuff>]"
            );
        }
        this.keyword = keyword.trim().toLowerCase();
    }

    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        assert tasks != null : "tasks cannot be null";

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(this.keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "I cannot find any tasks with " + this.keyword + " in their description.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(".").append(matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String getSimpleName() {
        return "FindCommand";
    }
}
