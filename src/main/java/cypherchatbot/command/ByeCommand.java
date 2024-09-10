package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class ByeCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }
}
