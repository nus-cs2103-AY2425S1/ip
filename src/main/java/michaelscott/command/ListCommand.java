package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.TaskList;

/**
 * Represents a command to display the taskList.
 * This command implements the Command interface.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks) throws MichaelScottException {
        if (tasks.size() == 0) {
            return "The list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }
}
