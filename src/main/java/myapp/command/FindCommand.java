package myapp.command;

import myapp.core.BingBongUi;
import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, BingBongUi ui, Storage storage) {

        StringBuilder list = new StringBuilder("Here are your matching tasks in your list:\n");
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                list.append(count).append(". ").append(task).append("\n");
                hasTasks = true;
                count++;
            }
        }

        if (!hasTasks) {
            ui.showResponse("No tasks found that match keyword '" + keyword + "'.");
        } else {
            ui.showResponse(list.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
