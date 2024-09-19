package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

public class FindCommand implements Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.showTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
