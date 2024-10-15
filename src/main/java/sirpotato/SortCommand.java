package sirpotato;

/**
 * Without modifying the task list, displays a modified task list
 * sorted by, either, description or deadline
 */
public class SortCommand extends Command {

    private String categoryToSortBy;

    public SortCommand(String categoryToSortBy) {
        this.categoryToSortBy = categoryToSortBy;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.sortBy(categoryToSortBy);
        return ui.displaySortedTasks(tasks);
    }

}
