package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

/**
 * Represent command to find task with certain keyword.
 */
public class FindCommand extends TextCommand {
    private String filter;

    /**
     * Create a FindCommand Object with keyword initialized to be "find";
     * @param filter can be parsed to key used to search in task list.
     */
    public FindCommand(String filter) {
        super("find");
        filter = filter.substring(5);
        this.filter = filter;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList target = tasks.find(filter);
        return ui.showTargetTask(target);
    }
}
