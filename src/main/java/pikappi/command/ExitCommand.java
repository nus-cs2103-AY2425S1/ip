package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        storage.save(tasks);
    }
}
