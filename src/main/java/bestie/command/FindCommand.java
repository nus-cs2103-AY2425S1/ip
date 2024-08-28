package bestie.command;

import bestie.Storage;
import bestie.Task;
import bestie.TaskList;
import bestie.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.showFoundTasks(matchingTasks);
    }

}
