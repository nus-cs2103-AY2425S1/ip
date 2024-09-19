package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents unmark command.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        int index = super.parseIndex();
        tasks.markTask(index, false);
        return "I have marked task " + (index + 1) + " as undone:\n" + tasks.listTasks();
    }
}
