package cypherchatbot.command;

import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.util.ArrayList;


public class FindCommand extends Command {
    private String substring;

    public FindCommand(String s) {
        this.substring = s.toLowerCase();
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredList = tasks.filterTasks(this.substring);
        return ui.showFilterMessage(filteredList);
    }

    public boolean isExit() {
        return false;
    }
}
