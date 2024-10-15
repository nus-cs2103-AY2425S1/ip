package sirpotato;

/**
 * Finds a task in the tasklist that matches the search string
 */
public class FindCommand extends Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.findTasks(tasks, searchString);
    }

}
