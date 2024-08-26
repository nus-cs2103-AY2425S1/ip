package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

public class BadCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }
}
