package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class HelpCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpMessage();
    }

    public boolean showExitStatus() {
        return false;
    }
}
