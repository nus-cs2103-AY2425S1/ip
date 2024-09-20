package mittens.commands;

import java.util.ArrayList;
import java.util.List;

import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for finding tasks that match a keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(this.keyword);
        List<String> messages = new ArrayList<>();

        int count = matchingTasks.size();
        if (count == 0) {
            messages.add("Meow?! I couldn't find anything with that keyword!");
        } else {
            messages.add("I found %d tasks with that keyword, here they are :3".formatted(count));
        }

        for (int i = 0; i < count; i++) {
            Task task = tasks.getTask(i);
            messages.add("%d. %s".formatted(i + 1, task));
        }

        ui.showRegularMessage(messages);
    }
}
