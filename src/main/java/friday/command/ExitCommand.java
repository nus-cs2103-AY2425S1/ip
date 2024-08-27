package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

