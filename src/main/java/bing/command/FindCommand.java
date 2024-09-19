package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

import java.io.IOException;

public class FindCommand implements Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
