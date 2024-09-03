package PHambot.command;

import PHambot.task.Task;
import PHambot.utils.Utilities;

import java.util.ArrayList;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean executeCommand() {
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            Utilities.OutlineMessage("No tasks found.");
            return false;
        }
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            message = message + (i + 1) + ":" + foundTasks.get(i) + "\n";
        }
        Utilities.OutlineMessage(message);
        return true;
    }
}
