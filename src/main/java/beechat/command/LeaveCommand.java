package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

public class LeaveCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isLeave() {
        return true;
    }
}