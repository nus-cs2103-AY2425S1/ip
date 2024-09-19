package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents mark command.
 */
public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        int index = super.parseIndex();
        tasks.markTask(index, true);
        return "I have marked task " + (index + 1) + " as done:\n" + tasks.listTasks();
    }
}
