package nugget.command;

import nugget.*;
import nugget.exception.NuggetException;

import java.util.ArrayList;

public class FindTaskCommand implements Command {
    private String searchQuery;

    public FindTaskCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        // Find matching tasks
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(searchQuery);

        // Display the matching tasks
        ui.showFindResults(matchingTasks);
    }
}
