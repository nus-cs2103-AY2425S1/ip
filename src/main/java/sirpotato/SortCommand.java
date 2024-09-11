package sirpotato;

public class SortCommand extends Command {

    private String categoryToSortBy;

    public SortCommand(String categoryToSortBy) {
        this.categoryToSortBy = categoryToSortBy;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.sortBy(categoryToSortBy);
        //System.out.println(SORTED_LIST_MSG + ui.listTasks(tasks.sortBy(categoryToSortBy)));
        return ui.displaySortedTasks(tasks);
    }
    
}