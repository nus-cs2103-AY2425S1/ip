package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("%n%d. %s".formatted(i + 1, tasks.get(i)));
        }
        ui.showToUser(sb.toString());
    }
}
