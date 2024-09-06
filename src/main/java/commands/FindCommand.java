package commands;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exceptions.HandsomeException;

import java.util.List;

/**
 * Finds the corresponding tasks with the keyword requested by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HandsomeException {
        List<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.isEmpty()) {
            return "Sadly, no matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("I got you, these are the matching tasks! \n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append((i + 1)).append(".").append(matchedTasks.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }
}
