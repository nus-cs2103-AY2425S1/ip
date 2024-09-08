package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

/**
 * Command to find list of tasks with description matching given query.
 */
public class FindCommand extends AbstractCommand {
    private final String query;

    /**
     * Construct new find command with given string query.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Execute find command and return string containing search results to be printed.
     */
    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        TaskList searchResults = taskList.findTasksMatchingDescription(query);
        if (searchResults.isEmpty()) {
            return Optional.of("NO TASK DESCRIPTIONS MATCH THE QUERY");
        }
        return Optional.of("SEARCH RESULTS:\n" + searchResults.toString());
    }
}
