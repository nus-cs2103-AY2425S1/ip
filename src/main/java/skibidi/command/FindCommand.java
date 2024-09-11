package skibidi.command;

import java.util.List;
import java.util.stream.Collectors;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/** Command to find list of tasks with description matching given query. */
public class FindCommand extends AbstractCommand {
    private final String query;

    /** Construct new find command with given string query. */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Execute find command and return string containing search results to be printed.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        List<AbstractTask> searchResults = taskList.findTasksMatchingDescription(query);
        if (searchResults.isEmpty()) {
            return "NO TASK DESCRIPTIONS MATCH THE QUERY";
        }
        String result = searchResults.stream()
                .map(task -> "\t" + task.toString())
                .collect(Collectors.joining("\n"));
        return "SEARCH RESULTS:\n" + result;
    }
}
