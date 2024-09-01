package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.completeModification();
        ui.showOutput("Bye. Hope to see you again!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
