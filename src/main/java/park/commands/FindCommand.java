package park.commands;

import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

/**
 * Represents a command that finds tasks that matches a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object
     *
     * @param keyword Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int counter = 1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            if (task.contains(keyword)) {
                result.append(counter).append(".").append(task).append("\n");
                counter++;
            }
        }
        if (result.isEmpty()) {
            ui.showToUser("There are no matching tasks.");
        } else {
            ui.showToUser("Here are the matching tasks in your list:");
            ui.showToUser(result.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
