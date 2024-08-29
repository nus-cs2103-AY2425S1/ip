package fanny.command;

import fanny.task.Task;
import fanny.task.TaskList;
import fanny.ui.Ui;

import java.util.List;

public class FindCommand extends Command {

    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        List<Task> filteredList = list.findTasks(this.keyword);
        if (filteredList.isEmpty()) {
            ui.showMessage("Fanny:\nNo matching tasks found.");
        } else {
            ui.showMessage("Fanny:\nHere are the matching tasks in your list:");
            for (int i = 0; i < filteredList.size(); i++) {
                ui.showMessage((i + 1) + "." + filteredList.get(i).toString());
            }
        }
        ui.showHorizontalLine();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
