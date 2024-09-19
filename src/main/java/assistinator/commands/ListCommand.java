package assistinator.commands;

import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents list command.
 */
public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.listTasks();
    }
}
