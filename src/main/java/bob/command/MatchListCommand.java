package bob.command;

import bob.Storage;
import bob.TaskList;

public class MatchListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Did you mean 'list'?";
    }
}

