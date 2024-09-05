package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("%n%d. %s".formatted(i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
