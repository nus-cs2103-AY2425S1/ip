package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class ByeCommand extends Command {

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }
}
