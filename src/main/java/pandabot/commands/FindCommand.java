package pandabot.commands;


import java.util.ArrayList;

import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.tasks.Task;
import pandabot.ui.Ui;

/**
 * The FindCommand class implements the Command interface and represents a command to find tasks
 * in the task list that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for within task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.isEmpty()) {
            return "No keyword provided. To find tasks by keywords, use: find <keyword>";
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks with descriptions containing '").append(keyword).append("' in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                String item = (i + 1) + ". " + matchingTasks.get(i) + "\n";
                sb.append(item);
            }
            return sb.toString();
        }
    }
}
