package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * The FindCommand class implements the Command interface and represents a command to find tasks
 * in the task list that contain a specific keyword.
 */
 public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for within task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.isEmpty()) {
            ui.show("No keyword provided. To find tasks by keywords, use: find <keyword>");
            return;
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        ui.printLine();
        if (matchingTasks.isEmpty()) {
            ui.show("No matching tasks found.");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.show((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
