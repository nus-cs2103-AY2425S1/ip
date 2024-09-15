package orion.commands;

import java.util.List;

import orion.exceptions.OrionInputException;
import orion.utils.Parser;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to find and list tasks that match a specific query.
 */
public class FindCommand extends Command {

    /** The query string used to find matching tasks. */
    private String query;

    /**
     * Constructs a {@code FindCommand} with the specified query.
     *
     * @param query the query string to search for in the task descriptions
     */
    public FindCommand(String query) {
        super(false);
        this.query = query;
    }

    public FindCommand(String[] command) throws OrionInputException {
        super(false);
        if (command.length < 2) {
            throw new OrionInputException("Correct syntax: find <search query>");
        } else {
            query = Parser.removeFirstWordFromString(String.join(" ", command)).trim();
        }
    }

    /**
     * Executes the find command by searching for tasks that match the query and printing them.
     * The matching tasks are obtained from the {@link TaskList} and their descriptions are printed.
     *
     * @param tasks the {@link TaskList} containing the tasks to search through
     * @param storage the {@link Storage} object used for data storage (not used in this method)
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<String> taskDescriptions = tasks.getMatchingTasks(query);
        if (taskDescriptions.isEmpty()) {
            return "Your task list is empty! Well done!";
        }

        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:");
        for (String s : taskDescriptions) {
            stringBuilder.append("\n");
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
