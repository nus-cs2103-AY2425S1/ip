package Tuesday.command;

import Tuesday.task.Task;
import Tuesday.util.Storage;
import Tuesday.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private final String commandPostfix;

    public FindCommand(String command,String commandPostfix) {
        super(command);
        this.commandPostfix = commandPostfix;
    }

    public void execute(Task task, Ui ui, Storage storage) {
        ui.showFindMessage(this.commandPostfix);
    }

    public boolean isExit() {
        return false;
    }
}
