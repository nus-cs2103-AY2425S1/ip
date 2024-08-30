package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append("%n%d. %s".formatted(i + 1, task));
            }
        }
        ui.showToUser(sb.toString());
    }
}
