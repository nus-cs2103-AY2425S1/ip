package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        System.out.println("Invalid Trackie.Command.");
    }

}
