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
        StringBuilder matchingTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            if (task.contains(keyword)) {
                matchingTasks.append(counter).append(".").append(task).append("\n");
                counter++;
            }
        }
        ui.setFindResponse(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
