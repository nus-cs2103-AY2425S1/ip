package sage.Command;

import sage.List.TaskList;
import sage.Storage;
import sage.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks);
            ui.showGoodbye();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
