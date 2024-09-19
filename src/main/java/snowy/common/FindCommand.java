package snowy.common;

import snowy.tasklist.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Finds and lists all tasks in the task list whose description contains any of the argument keywords.
 * Keyword matching is case-sensitive.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        final List<Task> tasksFound = findMatchingTasks(keyword);
        return new CommandResult(displayMatchingTasks(tasksFound));
    }

    /**
     * Retrieves all tasks in the task list whose descriptions contain some of the specified keywords.
     *
     * @param keyword for searching
     * @return list of tasks found
     */
    private List<Task> findMatchingTasks(String keyword) {
        final List<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Formats the task list for display.
     */
    private String displayMatchingTasks(List<Task> tasks) {
        StringBuilder formattedTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String index = String.format("%d. ", i + 1);
            formattedTasks.append(index).append(tasks.get(i).toString()).append("\n");
        }
        return String.format("%s\n%d task(s) listed!", formattedTasks, tasks.size());
    }
}
