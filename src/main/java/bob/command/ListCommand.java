package bob.command;

import bob.Storage;
import bob.TaskList;

/**
 * Represents a command that lists the tasks a user has created.
 */
public class ListCommand extends Command {

    /**
     * Executes the task, listing all tasks a user has created.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return String.format("These are your tasks:\n%s", tasks);
    }
}
