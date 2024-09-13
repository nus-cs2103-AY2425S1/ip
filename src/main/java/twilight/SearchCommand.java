package twilight;

/**
 * Represents a command to search the task list for a particular term.
 */
public class SearchCommand extends Command {
    protected String query;

    /**
     * Instantiates a search command.
     */
    public SearchCommand(String query) {
        this.query = query;
    }

    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        return tasks.query(query);
    }
}