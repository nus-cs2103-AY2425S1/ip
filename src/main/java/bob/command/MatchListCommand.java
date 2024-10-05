package bob.command;

import bob.Storage;
import bob.TaskList;

/**
 * Represents a command that assists the user in using the list instruction correctly.
 */
public class MatchListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Did you mean 'list'?";
    }
}

