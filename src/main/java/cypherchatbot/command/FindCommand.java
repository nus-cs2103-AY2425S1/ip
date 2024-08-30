package cypherchatbot.command;

import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.util.Locale;

public class FindCommand extends Command {
    String substring;

    public FindCommand(String s) {
        this.substring = s.toLowerCase();
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.filterTasks(this.substring);
        ui.output(output);
    }

    public boolean isExit() {
        return false;
    }
}
