package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

public class FindCommand extends AbstractCommand {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        TaskList searchResults = taskList.findTasksMatchingDescription(query);
        if (searchResults.isEmpty()) {
            return Optional.of("NO TASK DESCRIPTIONS MATCH THE QUERY");
        }
        return Optional.of("SEARCH RESULTS:\n" + searchResults.toString());
    }
}
