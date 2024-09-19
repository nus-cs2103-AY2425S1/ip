package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchWord;

    public FindCommand (String searchWord) {
        this.searchWord = searchWord;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = list.findTasks(searchWord);
        return ui.showMatchingTasks(matchingTasks);
    }
}
