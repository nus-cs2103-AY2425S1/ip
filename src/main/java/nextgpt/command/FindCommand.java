package nextgpt.command;

import nextgpt.Storage;
import nextgpt.TaskList;
import nextgpt.task.Task;
import nextgpt.Ui;


/**
 * Subclass of Command that finds tasks in task list with given keyword.
 */

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks in saved tasklist that contains keyword.
     *
     * @param tasks Task list to search keyword in.
     * @param ui User interface that notifies user of completion.
     * @param storage Not required.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null: "Storage cannot be NULL!";
        assert tasks != null: "Ui cannot be NULL!";
        assert storage != null: "Task list cannot be NULL!";

        TaskList resultsFound = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                Task foundTask = tasks.get(i);
                resultsFound.add(foundTask);
            }
        }
        return ui.showList(resultsFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
