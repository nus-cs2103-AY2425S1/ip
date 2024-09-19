package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents delete command.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        int deleteIndex = super.parseIndex();
        tasks.deleteTask(deleteIndex);
        return String.format(
                "Task %d deleted successfully\nYour evil agenda contains %d%s",
                deleteIndex + 1,
                tasks.size(),
                tasks.size() == 1 ? " task" : " tasks"
        );
    }
}
