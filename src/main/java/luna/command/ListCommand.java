package luna.command;

import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to list all tasks in list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.list();
    }
}
