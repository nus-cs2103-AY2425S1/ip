package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        storage.save(tasklist);
        ui.sayGoodbyeMessage();
    }
}
