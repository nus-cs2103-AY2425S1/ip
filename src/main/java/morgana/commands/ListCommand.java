package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
