package kobe.command;

import kobe.task.TaskList;
import kobe.util.Storage;
import kobe.util.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks);
    }
}
