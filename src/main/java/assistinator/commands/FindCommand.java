package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents find command.
 */
public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        String keyword = super.parseKeyword();
        return "I found these related tasks:\n"
                + tasks.filterTasks(keyword);
    }
}
