package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents invalid command.
 */
public class InvalidCommand extends Command {
    public InvalidCommand(String input) {
        super(input);
    }

    /**
     * Returns exception to alert user that command does not exist.
     * @param tasks Task list.
     * @param storage Storage.
     * @return Not relevant.
     * @throws AssistinatorException Command does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        throw new AssistinatorException("This command does not exist doctor");
    }
}
