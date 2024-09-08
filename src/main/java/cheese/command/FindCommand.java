package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

/**
 * Command to search through tasks
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Creates a FindCommand, requires keyword to find task
     * @param keyword string to query against
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Returns string of tasks with name that is a substring of the keyword
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getName().contains(query)) {
                matchedTasks.add(t);
            }
        }
        return ui.say(matchedTasks);
    }
}
