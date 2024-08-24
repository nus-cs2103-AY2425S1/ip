package luna.command;

import luna.Storage;
import luna.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.list();
    }
}
