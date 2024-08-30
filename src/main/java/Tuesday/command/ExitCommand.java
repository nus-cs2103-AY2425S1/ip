package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
