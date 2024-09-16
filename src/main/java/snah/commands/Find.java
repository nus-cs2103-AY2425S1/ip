package snah.commands;

import java.util.ArrayList;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.task.Task;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Find command to find tasks in the task list
 */
public class Find extends Command {
    public Find(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        String keyword = Parser.getSearchQuery(getInput());

        ArrayList<Task> searchResults = tasks.search(keyword);

        if (searchResults.isEmpty()) {
            return "No tasks found with the keyword";
        }

        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < searchResults.size(); i++) {
            response += String.format("%d. %s\n", i + 1, searchResults.get(i));
        }
        return response;
    }

}
