package bob.command;

import bob.Storage;
import bob.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return String.format("These are your tasks:\n%s", tasks);
    }
}
