package sirpotato;

public class SortCommand extends Command {

    private String categoryToSortBy;
    private final String SORTED_LIST_MSG = "List has been sorted. Here is your new list: \n";

    public SortCommand(String categoryToSortBy) {
        this.categoryToSortBy = categoryToSortBy;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.sortBy(categoryToSortBy);
        //System.out.println(SORTED_LIST_MSG + ui.listTasks(tasks.sortBy(categoryToSortBy)));
        return SORTED_LIST_MSG + ui.displaySortedTasks(tasks);
    }
    
}