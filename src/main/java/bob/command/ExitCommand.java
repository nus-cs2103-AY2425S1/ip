package bob.command;

import bob.Storage;
import bob.TaskList;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}