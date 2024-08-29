package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class HelpCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.output("<<UNDER CONSTRUCTION>>");
    }

    public boolean isExit() {
        return false;
    }
}
