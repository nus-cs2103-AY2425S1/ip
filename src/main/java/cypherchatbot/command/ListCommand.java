package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class ListCommand extends Command {

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.toString();
        ui.output(task);
    };
    public boolean isExit() {
        return false;
    }
}
