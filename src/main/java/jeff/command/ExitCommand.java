package jeff.command;

import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
