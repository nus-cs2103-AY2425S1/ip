package rizz.command;

import rizz.source.TaskList;

public abstract class SaveableCommand extends Command{

    @Override
    public String execute(TaskList tasks) {
        return null;
    }
}
