package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.Task;
import mryapper.task.TaskList;

import java.util.ArrayList;

public class FindTask extends Command {

    private final String searchInput;

    public FindTask(String searchInput) {
        this.searchInput = searchInput;
    }

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
        assert !searchInput.isEmpty(): "searchInput should not be empty";

        ArrayList<Task> searchResult = tasks.searchTasks(searchInput);
        if (searchResult.isEmpty()) {
            return "None of the tasks match your search results!";
        }

        assert searchResult.size() > 0: "There should be at least one task in search results";

        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < searchResult.size(); i++ ) {
            int taskNumber = i + 1;
            response += taskNumber + "." + searchResult.get(i).toString() + "\n";
        }
        return response;
    }

    /**
     * Gets the syntax of the command.
     *
     * @return The syntax of the command to find command.
     */
    public static String getSyntax() {
        return "e.g. find do project";
    }
}
