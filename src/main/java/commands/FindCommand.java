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
        if (matchingTasks.isEmpty()) {
            ui.printMessage("No matching tasks found.");
        } else {
            ui.printMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        return true;
    }
}