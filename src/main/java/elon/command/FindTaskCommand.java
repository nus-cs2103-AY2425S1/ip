package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.util.ArrayList;

public class FindTaskCommand extends Command {
    private String searchWord;

    public FindTaskCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = list.findTasks(searchWord);
        return ui.showMatchingTasks(matchingTasks);
    }
}
