package duck.commands;

import duck.data.TaskList;
import duck.storage.Storage;
import duck.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Hey, that's not a valid instruction! I don't know how to respond to that.\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
