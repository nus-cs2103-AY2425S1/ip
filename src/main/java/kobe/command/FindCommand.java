package kobe.command;

import kobe.task.Task;
import kobe.task.TaskList;
import kobe.util.Storage;
import kobe.util.Ui;

/**
 * Represents a command to find tasks based on a keyword or tag.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = keyword.startsWith("#")
                ? tasks.findTasksByTag(keyword.substring(1))
                : tasks.findTasks(keyword);
        ui.setResponse("Here are the matching tasks in your list:\n" + matchingTasks.getAllTasksAsString());
    }
}
