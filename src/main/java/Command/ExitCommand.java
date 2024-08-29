package Command;

import List.TaskList;
import Sage.Storage;
import Sage.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showGoodbye();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
