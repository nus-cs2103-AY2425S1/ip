package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
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

        TaskList searchResults = tasks.search(keyword);

        if (searchResults.size() == 0) {
            return "No tasks found with the keyword";
        }

        String response = "Here are the tasks in your list:\n";
        response += searchResults.list();
        return response;
    }

}
