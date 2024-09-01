package commands;

import skibidi.Command;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Task;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.substring(5).trim();
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        List<Task> matchingTasks = storage.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.printMessage("No matching tasks found.");
        } else {
            StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                str.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.printMessage(str.toString());
        }
        return true;
    }
}