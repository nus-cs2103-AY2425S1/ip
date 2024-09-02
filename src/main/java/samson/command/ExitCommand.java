package samson.command;// Samson.Samson.Command.ExitCommand.java

import samson.Storage;
import samson.Ui;
import samson.task.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

