package chobo;

import java.util.ArrayList;

/**
 * Represent a Find command which search for keywords.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a new Find command.
     *
     * @param keyword Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getTaskName().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return ui.showMatchedTasks(matchedTasks);
    }
}
