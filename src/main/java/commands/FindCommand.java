package commands;

import skibidi.Command;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Task;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        List<Task> matchingTasks = storage.findTasksByKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
        return true;
    }
}