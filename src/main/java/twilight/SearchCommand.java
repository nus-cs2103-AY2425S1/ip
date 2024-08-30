package twilight;

import java.io.IOException;

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

    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        System.out.println(tasks.query(query));
    }
}