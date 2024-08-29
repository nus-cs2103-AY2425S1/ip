package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
