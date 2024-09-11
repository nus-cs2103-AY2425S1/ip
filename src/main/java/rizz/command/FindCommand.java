package rizz.command;

import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;


public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that contain the keyword
     * and displaying the matching tasks.
     *
     * @param tasks The TaskList to search through.
     * @param ui The Ui instance used to display the results.
     * @param storage The Storage instance (not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findByKeyword(keyword);
        ui.showMatchingTasks(foundTasks);
    }
}
