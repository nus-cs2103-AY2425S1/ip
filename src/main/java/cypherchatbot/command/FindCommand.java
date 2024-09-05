package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;


public class FindCommand extends Command {
    private String substring;

    public FindCommand(String s) {
        this.substring = s.toLowerCase();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.filterTasks(this.substring);
        ui.output(output);
    }

    public boolean isExit() {
        return false;
    }
}
